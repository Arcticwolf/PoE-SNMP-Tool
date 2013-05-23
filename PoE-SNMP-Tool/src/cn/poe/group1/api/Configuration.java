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
    
    /**
     * Returns the amount of slots which should be created in the measurement 
     * interval. All SwitchDataCollector instances which are created are 
     * distributed equally in the slots to avoid performance requirement peaks. 
     * In case of an error or if the value is not set, the default value 10 is
     * returned.
     * @return The amount of slots which should be created in the measurement 
     * interval.
     */
    int getDistributionSlots();
    
    /**
     * Returns the class name of the data retriever implementation. In case of
     * an error or if the value is not set, the default value of the 
     * DummyDataRetriever is returnde
     * @return the implementing class of the data retriever interface.
     */
    String getDataRetrieverImpl();
}
