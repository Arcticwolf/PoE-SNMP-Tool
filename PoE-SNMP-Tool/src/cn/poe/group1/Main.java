package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.collector.SwitchDataCollector;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import cn.poe.group1.gui.PoESNMPToolGUI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point of the poe snmp diagnose tool.
 */
public class Main {
    private static final String FACTORY_NAME = "poe-snmp-tool";
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private MeasurementBackend measurementBackend;
    private HashMap<String, SwitchDataCollector> collectors;
    private Configuration config;
    private EntityManagerFactory factory;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
    }
    
    public Main() throws IOException {
        this.config = new PropertyFileConfig();
        this.collectors = new HashMap<>();
        this.factory = Persistence.createEntityManagerFactory(FACTORY_NAME);
        this.measurementBackend = new MeasurementDatabase(this.factory.createEntityManager());
        
        log.info("measurement interval : {}", config.getMeasurementInterval());
        
        // Just for testing impact of one MeasurementDatabase per thread
        for (int i=0; i<5; i++) {
            Switch sw = new Switch("testid_"+String.valueOf(i), "testIp", "testtype", 24, "testswitch");
            for (int j=0; j<sw.getPortCount(); j++) {
                Port p = new Port(sw, j, null);
                sw.addPort(p);
            }
            measurementBackend.persistSwitch(sw);
        }
        
        PoESNMPToolGUI.Main(measurementBackend, this);
        
        // Load all switches from DB 
        List<Switch> switches = measurementBackend.retrieveAllSwitches();
        log.info("Loaded switches from db. Found: {}", switches.size());
        for (Switch s : switches) {
            collectors.put(s.getIdentifier(), new SwitchDataCollector(s, config, new MeasurementDatabase(this.factory.createEntityManager())));
        }
        
        startCollecting();
        System.in.read();
        stopCollecting();
    }
    
    public void startCollecting() {
        for (SwitchDataCollector c : collectors.values()) {
            c.startCollecting();
        }
        log.info("Start collecting measurements. Press Enter to quit.");
    }
    
    public void stopCollecting() {
        for (SwitchDataCollector c : collectors.values()) {
            c.stopCollecting();
        }
        log.info("Quit taking measurements.");
    }
    
    public void addSwitch(Switch sw) {
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.put(sw.getIdentifier(), c);
        c.startCollecting();
    }
    
    public void removeSwitch(Switch sw) {
        SwitchDataCollector collector = collectors.get(sw.getIdentifier());
        if (collector != null) {
            collector.stopCollecting();
        }
        collectors.remove(sw.getIdentifier());
    }
}
