package cn.poe.group1.collector;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.RetrieverException;
import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PortDataCollector can collect a measurement from a given port object.
 */
public class PortDataCollector {
    private SNMPDataRetriever retriever;
    
    public PortDataCollector(Port port, Configuration config) {
        try {
            Class<?> clazz = Class.forName(config.getDataRetrieverImpl());
            Constructor con = clazz.getConstructor(Port.class);
            this.retriever = (SNMPDataRetriever) con.newInstance(port);
        } catch (Exception ex) {
            this.retriever = new DummyDataRetriever(port);
            Logger.getLogger(PortDataCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Measurement takeMeasurement() throws RetrieverException {
        return retriever.takeMeasurement();
    }   
}
