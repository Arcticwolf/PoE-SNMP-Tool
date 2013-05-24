package cn.poe.group1.api;

import cn.poe.group1.entity.Switch;

/**
 * The DataCollector interface represents the gate to the measurement taking
 * elements. 
 */
public interface DataCollector {

    /**
     * Adds a new Switch which should be monitored.
     * @param sw the Switch that should be monitored.
     */
    void addSwitch(Switch sw);
    
    /**
     * Removes a Switch from the monitoring system.
     * @param sw the Switch that should be removed.
     */
    void removeSwitch(Switch sw);
    
    /**
     * Shuts down the monitoring system.
     */
    void shutdown();
    
    /**
     * Updates the existing switch, and starts monitoring with new information
     * 
     * @param sw the switch that should be updated.
     */
    void updateSwitch(Switch sw);
}
