package cn.poe.group1.api;

import cn.poe.group1.entity.Measurement;

/**
 * This interface contains all methods which are needed to retrieve data from
 * a power-over-ethernet switch via SNMP.
 */
public interface SNMPDataRetriever {
    
    /**
     * Returns a measurment from a power-over-ethernet switch. The data what
     * actually be measured need to be defined in the implementing class.
     * @return A measurement object with the measured value
     */
    Measurement takeMeasurement();
    
}
