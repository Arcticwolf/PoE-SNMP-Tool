package cn.poe.group1.api;

import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Switch;
import java.util.Date;
import java.util.List;

/**
 * This interface contains all functions to save and retrieve measurements that
 * are saved by the diagnose tool.
 */
public interface MeasurementBackend {

    /**
     * Saves a measurement object to the measurement save backend.
     * @param measurement The measurment object that shall be persisted
     */
    void saveMeasurement(Measurement measurement);
    
    /**
     * Query for measurements which are persisted in the save backend.
     * @param switchId The id of the switch for which the measurement is taken
     * @param oid The SNMP oid of the measurement interested in
     * @param startTime The beginning time which decides which measurements are
     * considered in the query
     * @param endTime The end time which decides to which time the measurements
     * are considered in the query
     * @return The list of measurements which are match the query elements
     */
    List<Measurement> queryMeasurements(String switchId, String oid, 
            Date startTime, Date endTime);
    
    /**
     * Saves a switch object to the measurement backend.
     * @param sw The switch object that shall be persisted.
     */
    void saveSwitch(Switch sw);
    
    /**
     * Updates a switch object in the measurement backend.
     * @param sw The switch object that shall be updated.
     */
    void updateSwitch(Switch sw);

    /**
     * Deletes a switch object from the measurement backend.
     * @param sw The switch object that shall be deleted.
     */
    void deleteSwitch(Switch sw);
    
    /**
     * Returns a list of all switch objects which are currently persisted.
     * @return A list of all switch objects which are currently persisted.
     */
    List<Switch> retrieveAllSwitches();
}
