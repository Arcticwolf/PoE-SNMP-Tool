package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.collector.SwitchDataCollector;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Switch;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point of the poe snmp diagnose tool.
 */
public class Main {
    private static final String FACTORY_NAME = "poe-snmp-tool";
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private MeasurementBackend measurementBackend;

    public static void main(String[] args) throws IOException {
        Main main = new Main(new PropertyFileConfig());
    }
    
    public Main(Configuration config) throws IOException {
        log.info("measurement interval : {}", config.getMeasurementInterval());
        
        measurementBackend = new MeasurementDatabase(FACTORY_NAME);
        
        Switch sw = new Switch("testid", "testIp", "testtype", 2, "testswitch");
        measurementBackend.persistSwitch(sw);
        
        SwitchDataCollector collector = new SwitchDataCollector(sw, config, measurementBackend);
        collector.initPortBase();
        collector.startCollecting();
        log.info("Start collecting measurements. Press Enter to quit.");
        System.in.read();
        collector.stopCollecting();
        log.info("Quit taking measurements. Measurement count: {}", 
                measurementBackend.queryMeasurementsBySwitch(sw).size());
    }
}
