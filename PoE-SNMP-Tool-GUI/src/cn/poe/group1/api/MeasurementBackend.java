package cn.poe.group1.api;

import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
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
     * @param sw The switch for which the measurements were taken
     * @param startTime The beginning time which decides which measurements are
     * considered in the query
     * @param endTime The end time which decides to which time the measurements
     * are considered in the query
     * @return The list of measurements which are match the query elements
     */
    List<Measurement> queryMeasurementsBySwitch(Switch sw, Date startTime, Date endTime);
    
    /**
     * Query for measurements which are persisted in the save backend.
     * @param port The port for which the measurements were taken
     * @param startTime The beginning time which decides which measurements are
     * considered in the query
     * @param endTime The end time which decides to which time the measurements
     * are considered in the query
     * @return The list of measurements which are match the query elements
     */
    List<Measurement> queryMeasurementsByPort(Port port, Date startTime, Date endTime);
    
    /**
     * Query for all measurements for the given oid on the Switch with the 
     * given switch id.
     * @param sw The switch for which the measurements were taken
     * @param oidThe SNMP oid of the measurement interested in
     * @return The list of measurements which are match the query elements
     */
    List<Measurement> queryMeasurementsBySwitch(Switch sw);
    
    /**
     * Query for all measurements for the given Port.
     * @param port The port for which the measurements were taken
     * @return The list of measurements which are match the query elements
     */
    List<Measurement> queryMeasurementsByPort(Port port);
    
    /**
     * Saves or updates a switch object to the measurement backend.
     * @param sw The switch object that shall be persisted.
     */
    void persistSwitch(Switch sw);

    /**
     * Deletes a switch object from the measurement backend.
     * @param sw The switch object that shall be deleted.
     */
    void deleteSwitch(Switch sw);
    
    /**
     * Loads a switch object based on its id.
     * @param id The id of the switch that shall be loaded.
     * @return The loaded switch object or null if there is no such switch.
     */
    Switch getSwitchById(String id);
    
    /**
     * Returns a list of all switch objects which are currently persisted.
     * @return A list of all switch objects which are currently persisted.
     */
    List<Switch> retrieveAllSwitches();
    
    /**
     * Saves or updates a port object to the measurement backend.
     * @param port The port object that shall be persisted.
     */
    void persistPort(Port port);

    /**
     * Deletes a port object from the measurement backend.
     * @param port The port object that shall be deleted.
     */
    void deletePort(Port port);
    
    /**
     * Loads a port object based on its id.
     * @param id The id of the port that shall be loaded.
     * @return The loaded port object or null if there is no such port.
     */
    Port getPortById(Long id);
    
    /**
     * Returns a list of all port objects for a switch which are currently persisted.
     * @param sw The switch object for which all port objects shall be loaded.
     * @return A list of all port objects for a switch which are currently persisted.
     */
    List<Port> retrieveAllPorts(Switch sw);
}
