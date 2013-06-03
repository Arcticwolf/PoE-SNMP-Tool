package cn.poe.group1.api;

import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Switch;
import java.io.IOException;
import java.util.List;

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
    
    
    /**
     * Exports the given measurements in csv format to the file in the given
     * file path.
     * @param measurements The measurements that shall be exported.
     * @param filePath The path to the csv file.
     * @throws IOException Throws an exception if the writing to the file fails.
     */
    void exportMeasurements(List<Measurement> measurements, String filePath) 
            throws IOException;
}
