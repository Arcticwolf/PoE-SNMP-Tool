package cn.poe.group1.collector;

import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Switch;
import java.util.Date;

/**
 * This is a dummy implementation for the SNMPDataRetriever interface and is only
 * for mocking the real behaviour.
 */
public class DummyDataRetriever implements SNMPDataRetriever {
    private Switch sw;
    private String oid;
    
    public DummyDataRetriever(Switch sw, String oid) {
        this.sw = sw;
        this.oid = oid;
    }

    @Override
    public Measurement takeMeasurement() {
        Measurement measurement = new Measurement();
        measurement.setSwitch(sw);
        measurement.setOid(oid);
        measurement.setMeasureTime(new Date());
        measurement.setMeasuredValue((int) (Math.random() * 80) + "");
        return measurement;
    }

    
}
