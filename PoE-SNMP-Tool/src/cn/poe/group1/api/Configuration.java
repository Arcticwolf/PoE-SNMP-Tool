package cn.poe.group1.api;

/**
 * This interface contains all functions needed to retrieve the configuration
 * of the poe snmp diagnose tool. Separation from implementation is done for 
 * better testability.
 */
public interface Configuration {

    /**
     * Returns the time between two measurement takings over SNMP in milli 
     * seconds. In case of an error or if the value is not set, the default 
     * value 1000 is returned.
     * @return the time between two measurement takings over SNMP in milli 
     * seconds.
     */
    int getMeasurementInterval();
}
