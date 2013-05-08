package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Switch;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point of the poe snmp diagnose tool.
 */
public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private MeasurementBackend measurementBackend;

    public static void main(String[] args) {
        Main main = new Main(new PropertyFileConfig());
    }
    
    public Main(Configuration config) {
        log.info("measurement interval : {}", config.getMeasurementInterval());
        
        measurementBackend = new MeasurementDatabase("poe-snmp-tool");
        
        Switch sw = new Switch("testid", "testIp", "testtype", 20);   
        Switch sw2 = new Switch("testid2", "testIp2", "testtype2", 30);
        
        measurementBackend.persistSwitch(sw);
        measurementBackend.persistSwitch(sw2);
        
        List<Switch> result = measurementBackend.retrieveAllSwitches();
	for ( Switch res : result ) {
		System.out.println(res.toString());
	}
    }
}
