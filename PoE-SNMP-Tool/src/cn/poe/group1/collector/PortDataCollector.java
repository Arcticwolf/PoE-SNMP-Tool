package cn.poe.group1.collector;

import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;

/**
 * The PortDataCollector can collect a measurement from a given port object.
 */
public class PortDataCollector {
    private SNMPDataRetriever retriever;
    
    public PortDataCollector(Port port) {
        this.retriever = new DummyDataRetriever(port);
    }
    
    public Measurement takeMeasurement() {
        return retriever.takeMeasurement();
    }   
}
