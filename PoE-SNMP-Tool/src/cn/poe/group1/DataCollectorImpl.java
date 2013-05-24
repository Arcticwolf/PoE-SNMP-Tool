package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.DataCollector;
import cn.poe.group1.collector.SwitchDataCollector;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Switch;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;

/**
 * The implementation of the DataCollector interface. This implementation holds
 * all active SwitchDataCollector and distributes the collectors equally in 
 * time slots.
 */
public class DataCollectorImpl implements DataCollector {
    private Configuration config;
    private EntityManagerFactory factory;
    private Map<Switch, SwitchDataCollector> collectors;
    
    public DataCollectorImpl(Configuration config, EntityManagerFactory factory) {
        this.config = config;
        this.factory = factory;
        this.collectors = new HashMap<>();
    }    

    @Override
    public void addSwitch(Switch sw) {
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  
                new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.put(sw, c);
        int interval = config.getMeasurementInterval() / config.getDistributionSlots();
        int factor = (collectors.size() - 1) % config.getDistributionSlots();
        c.startCollecting(interval * factor);
    }

    @Override
    public void removeSwitch(Switch sw) {
        SwitchDataCollector collector = collectors.get(sw);
        if (collector != null) {
            collector.stopCollecting();
        }
        collectors.remove(sw);
    }
    
    @Override
    public void updateSwitch(Switch sw) {
        SwitchDataCollector collector = collectors.get(sw);
        if (collector != null) {
            collector.stopCollecting();
        }
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  
                new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.put(sw, c);
        int interval = config.getMeasurementInterval() / config.getDistributionSlots();
        int factor = (collectors.size() - 1) % config.getDistributionSlots();
        c.startCollecting(interval * factor);
    }

    @Override
    public void shutdown() {
        for (SwitchDataCollector collector : collectors.values()) {
            collector.stopCollecting();
        }
    }

}
