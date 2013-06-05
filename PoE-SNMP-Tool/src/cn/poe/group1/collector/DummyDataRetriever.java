package cn.poe.group1.collector;

import cn.poe.group1.api.RetrieverException;
import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.PortStatus;
import java.util.Date;

/**
 * This is a dummy implementation for the SNMPDataRetriever interface and is only
 * for mocking the real behaviour.
 */
public class DummyDataRetriever implements SNMPDataRetriever {
    private Port port;
    
    public DummyDataRetriever(Port port) {
        this.port = port;
    }

    @Override
    public Measurement takeMeasurement() throws RetrieverException {
        Measurement measurement = new Measurement();
        measurement.setPort(port);
        measurement.setCpeExtPsePortDeviceDetected(true);
        measurement.setCpeExtPsePortEnable(PortStatus.AUTO);
        measurement.setCpeExtPsePortMaxPwrDrawn(1150);
        measurement.setCpeExtPsePortPwrAllocated(1200);
        measurement.setCpeExtPsePortPwrAvailable(1300);
        measurement.setCpeExtPsePortPwrMax(1150);
        measurement.setCpeExtPsePortPwrConsumption((int) (Math.random() * 1200));
        measurement.setMeasureTime(new Date());
        return measurement;
    }

    
}
