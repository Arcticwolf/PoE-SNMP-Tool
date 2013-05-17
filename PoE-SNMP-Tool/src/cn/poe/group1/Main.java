package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.collector.SwitchDataCollector;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    private List<SwitchDataCollector> collectors;
    private Configuration config;
    private EntityManagerFactory factory;

    /*public static void main(String[] args) throws IOException {
        Main main = new Main();
    }*/
    
    public Main() throws IOException {
        this.config = new PropertyFileConfig();
        this.collectors = new ArrayList<>();
        this.factory = Persistence.createEntityManagerFactory(FACTORY_NAME);
        this.measurementBackend = new MeasurementDatabase(this.factory.createEntityManager());
        
        log.info("measurement interval : {}", config.getMeasurementInterval());
        
        // Just for testing impact of one MeasurementDatabase per thread
        for (int i=0; i<50; i++) {
            Switch sw = new Switch("testid_"+String.valueOf(i), "testIp", "testtype", 24, "testswitch");
            for (int j=0; j<sw.getPortCount(); j++) {
                Port p = new Port(sw, j, null);
                sw.addPort(p);
            }
            measurementBackend.persistSwitch(sw);
        }
        
        // Load all switches from DB 
        List<Switch> switches = measurementBackend.retrieveAllSwitches();
        log.info("Loaded switches from db. Found: {}", switches.size());
        for (Switch s : switches) {
            collectors.add(new SwitchDataCollector(s, config, new MeasurementDatabase(this.factory.createEntityManager())));
        }
        
        startCollecting();
        System.in.read();
        stopCollecting();
    }
    
    public void startCollecting() {
        for (SwitchDataCollector c : collectors) {
            c.startCollecting();
        }
        log.info("Start collecting measurements. Press Enter to quit.");
    }
    
    public void stopCollecting() {
        for (SwitchDataCollector c : collectors) {
            c.stopCollecting();
        }
        log.info("Quit taking measurements.");
    }
    
    public void addNewSwitch(String identifier, String ipAddress, String type, int portCount, String comment) {
        Switch sw = new Switch(identifier, ipAddress, type, portCount, comment);
        for (int i=0; i<portCount; i++) {
            Port p = new Port(sw, i, null);
            sw.addPort(p);
        }
        measurementBackend.persistSwitch(sw);
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.add(c);
        c.startCollecting();
    }
}
