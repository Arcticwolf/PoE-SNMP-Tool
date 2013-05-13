package cn.poe.group1.collector;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Switch;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The DataCollector collects in a perios defined in the configuration file data
 * about a combination of a switch and an oid.
 */
public class DataCollector {
    private Configuration configuration;
    private MeasurementBackend backend;
    private Timer timer;
    
    public DataCollector(Configuration configuration, MeasurementBackend backend) {
        this.configuration = configuration;
        this.backend = backend;
        timer = new Timer();
    }
    
    public void startCollecting(Switch sw, String oid) {
        final SNMPDataRetriever retriever = new DummyDataRetriever(sw, oid);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                backend.saveMeasurement(retriever.takeMeasurement());
            }
            
        }, 0, configuration.getMeasurementInterval());
    }
    
    public void stopCollecting() {
        timer.cancel();
    }
    
}
