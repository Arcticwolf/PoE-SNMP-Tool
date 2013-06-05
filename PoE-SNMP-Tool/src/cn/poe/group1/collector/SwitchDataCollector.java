package cn.poe.group1.collector;

import cn.poe.group1.Main;
import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.api.RetrieverException;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SwitchDataCollector collects in a period defined in the configuration file 
 * data from the given switch and all related ports.
 */
public class SwitchDataCollector {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private Configuration configuration;
    private MeasurementBackend backend;
    private List<PortDataCollector> ports;
    private Timer timer;
    
    public SwitchDataCollector(Switch sw, Configuration configuration, MeasurementBackend backend) {
        this.configuration = configuration;
        this.backend = backend;
        this.ports = new ArrayList<>();
        timer = new Timer();
        List<Port> swPorts = sw.getPorts();
        for (Port p : swPorts) {
            ports.add(new PortDataCollector(p, configuration));
        }
    }
       
    public void startCollecting(long delay) {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (PortDataCollector port : ports) {
                    try {
                        backend.saveMeasurement(port.takeMeasurement());
                    } catch (RetrieverException e) {
                        log.warn("Unable to read from port ", e);
                    }
                }
            }
            
        }, delay, configuration.getMeasurementInterval());
    }
    
    public void stopCollecting() {
        timer.cancel();
    }
}
