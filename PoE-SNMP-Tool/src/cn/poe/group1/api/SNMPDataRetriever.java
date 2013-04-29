package cn.poe.group1.api;

import cn.poe.group1.entity.Measurement;

/**
 * This interface contains all methods which are needed to retrieve data from
 * a power-over-ethernet switch via SNMP.
 */
public interface SNMPDataRetriever {
    
    /**
     * Connects to a power-over-ethernet switch and retrieves a measurement from
     * the given oid.
     * @param switchIp The ip where the switch is located
     * @param oid The oid of the value that shall be retrieved
     * @return A measurement object with the measured value
     */
    Measurement takeMeasurement(String switchIp, String oid);
    
}
