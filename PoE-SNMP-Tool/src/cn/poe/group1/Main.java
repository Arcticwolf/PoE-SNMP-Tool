package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.DataCollector;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import cn.poe.group1.gui.PoESNMPToolGUI;
import java.io.IOException;
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
    private Configuration config;
    private EntityManagerFactory factory;
    private DataCollector collector;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        log.info("Finished initializing. Start GUI.");
        PoESNMPToolGUI.Main(main.getBackend(), main.getDataCollector());
    }
    
    public Main() throws IOException {
        this.config = new PropertyFileConfig();
        this.factory = Persistence.createEntityManagerFactory(FACTORY_NAME);
        this.measurementBackend = new MeasurementDatabase(this.factory.createEntityManager());
        this.collector = new DataCollectorImpl(config, factory);
        
        log.info("measurement interval : {}", config.getMeasurementInterval());
        log.info("distribution slots : {}", config.getDistributionSlots());
        log.info("data retriever implementation : {}", config.getDataRetrieverImpl());
        
        addTestSwitches();
        
        // Load all switches from DB 
        List<Switch> switches = measurementBackend.retrieveAllSwitches();
        log.info("Loaded switches from db. Found: {}", switches.size());
        for (Switch s : switches) {
            collector.addSwitch(s);
        }        
    }
    
    private void addTestSwitches() {
        // Just for testing impact of one MeasurementDatabase per thread
        //for (int i=0; i<5; i++) {
            //Switch sw = new Switch("testid_"+String.valueOf(i), "testIp", "testtype", 24, "testswitch");
            Switch sw = new Switch("testid", "128.131.30.84", "testtype", 25, "testswitch");
            for (int j=1; j<=sw.getPortCount(); j++) {
                Port p = new Port(sw, j, null);
                sw.addPort(p);
            }
            measurementBackend.persistSwitch(sw);
        //}
    }
    
    public MeasurementBackend getBackend() {
        return this.measurementBackend;
    }
    
    public DataCollector getDataCollector() {
        return this.collector;
    }
}
