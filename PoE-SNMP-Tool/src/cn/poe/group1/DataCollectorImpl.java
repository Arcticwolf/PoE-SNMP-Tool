package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import cn.poe.group1.api.DataCollector;
import cn.poe.group1.collector.SwitchDataCollector;
import cn.poe.group1.db.MeasurementDatabase;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;

/**
 * The implementation of the DataCollector interface. This implementation holds
 * all active SwitchDataCollector and distributes the collectors equally in 
 * time slots.
 */
public class DataCollectorImpl implements DataCollector {
    private Configuration config;
    private EntityManagerFactory factory;
    private Map<Switch, SwitchDataCollector> collectors;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    
    public DataCollectorImpl(Configuration config, EntityManagerFactory factory) {
        this.config = config;
        this.factory = factory;
        this.collectors = new HashMap<>();
    }    

    @Override
    public void addSwitch(Switch sw) {
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  
                new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.put(sw, c);
        int interval = config.getMeasurementInterval() / config.getDistributionSlots();
        int factor = (collectors.size() - 1) % config.getDistributionSlots();
        c.startCollecting(interval * factor);
    }

    @Override
    public void removeSwitch(Switch sw) {
        SwitchDataCollector collector = collectors.get(sw);
        if (collector != null) {
            collector.stopCollecting();
        }
        collectors.remove(sw);
    }
    
    @Override
    public void updateSwitch(Switch sw) {
        SwitchDataCollector collector = collectors.get(sw);
        if (collector != null) {
            collector.stopCollecting();
        }
        SwitchDataCollector c = new SwitchDataCollector(sw, config,  
                new MeasurementDatabase(this.factory.createEntityManager()));
        collectors.put(sw, c);
        int interval = config.getMeasurementInterval() / config.getDistributionSlots();
        int factor = (collectors.size() - 1) % config.getDistributionSlots();
        c.startCollecting(interval * factor);
    }

    @Override
    public void shutdown() {
        for (SwitchDataCollector collector : collectors.values()) {
            collector.stopCollecting();
        }
    }

    @Override
    public void exportMeasurements(List<Measurement> measurements, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filePath))) {
            writeHeader(writer);
            for (Measurement measurement : measurements) {
                writeMeasurement(measurement, writer);
            }
            writer.flush();
        }
    }
    
    /**
     * Writes a measurement to a csv file.
     * @param measurement The measurement that shall be written.
     * @param writer The writer to which the measurement shall be written.
     */
    private void writeMeasurement(Measurement measurement, PrintWriter writer) {
        Port port = measurement.getPort();
        Switch sw = port.getSw();
        writer.append(sw.getIdentifier()).append(";").append(sw.getType()).append(";");
        writer.append(port.getPortNumber()+"").append(";");
        writer.append(dateFormat.format(measurement.getMeasureTime()) + ";");
        writer.append(measurement.getCpeExtPsePortDeviceDetected() + ";");
        writer.append(measurement.getCpeExtPsePortEnable() + ";");
        writer.append(measurement.getCpeExtPsePortPwrMax() + ";");
        writer.append(measurement.getCpeExtPsePortPwrAllocated() + ";");
        writer.append(measurement.getCpeExtPsePortPwrAvailable() + ";");
        writer.append(measurement.getCpeExtPsePortPwrConsumption() + ";");
        writer.append(measurement.getCpeExtPsePortMaxPwrDrawn() + "\r\n");
        writer.flush();
    }

    /**
     * Writes the header for the csv file.
     * @param writer The writer to which the header shall be written.
     */
    private void writeHeader(PrintWriter writer) {
        writer.append("switch id;switch type;port number;measure time;")
                .append("cpeExtPsePortDeviceDetected;cpeExtPsePortPortEnable;")
                .append("cpeExtPsePortPwrMax;cpeExtPsePortPwrAllocated;")
                .append("cpeExtPsePortPwrAvailable;cpeExtPsePortPwrConsumption;")
                .append("cpeExtPsePortMaxPwrDrawn\r\n");
        writer.flush();
    }

}
