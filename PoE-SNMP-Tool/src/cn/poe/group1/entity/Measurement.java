package cn.poe.group1.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * This is the model of a measurement retrieved from a SNMP switch.
 */
@Entity
@Table( name = "MEASUREMENT" )
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="sequence_generator")
    @SequenceGenerator(name="sequence_generator", sequenceName="SEQUENCER")
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    private Port port;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date measureTime;
    
    private PortStatus cpeExtPsePortEnable;
    private Boolean cpeExtPsePortDeviceDetected;
    private Integer cpeExtPsePortPwrMax; // milliwatts
    private Integer cpeExtPsePortPwrAllocated; // milliwatts
    private Integer cpeExtPsePortPwrAvailable; // milliwatts
    private Integer cpeExtPsePortPwrConsumption; // milliwatts
    private Integer cpeExtPsePortMaxPwrDrawn; // milliwatts
    
    
    public Measurement() {
        // needs to be here because of hibernate
    }
    
    public Measurement(Port port, Date measureTime) {
        this.port = port;
        this.measureTime = measureTime;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PortStatus getCpeExtPsePortEnable() {
        return cpeExtPsePortEnable;
    }

    public void setCpeExtPsePortEnable(PortStatus cpeExtPsePortEnable) {
        this.cpeExtPsePortEnable = cpeExtPsePortEnable;
    }

    public Boolean getCpeExtPsePortDeviceDetected() {
        return cpeExtPsePortDeviceDetected;
    }

    public void setCpeExtPsePortDeviceDetected(Boolean cpeExtPsePortDeviceDetected) {
        this.cpeExtPsePortDeviceDetected = cpeExtPsePortDeviceDetected;
    }

    public Integer getCpeExtPsePortPwrMax() {
        return cpeExtPsePortPwrMax;
    }

    public void setCpeExtPsePortPwrMax(Integer cpeExtPsePortPwrMax) {
        this.cpeExtPsePortPwrMax = cpeExtPsePortPwrMax;
    }

    public Integer getCpeExtPsePortPwrAllocated() {
        return cpeExtPsePortPwrAllocated;
    }

    public void setCpeExtPsePortPwrAllocated(Integer cpeExtPsePortPwrAllocated) {
        this.cpeExtPsePortPwrAllocated = cpeExtPsePortPwrAllocated;
    }

    public Integer getCpeExtPsePortPwrAvailable() {
        return cpeExtPsePortPwrAvailable;
    }

    public void setCpeExtPsePortPwrAvailable(Integer cpeExtPsePortPwrAvailable) {
        this.cpeExtPsePortPwrAvailable = cpeExtPsePortPwrAvailable;
    }

    public Integer getCpeExtPsePortPwrConsumption() {
        return cpeExtPsePortPwrConsumption;
    }

    public void setCpeExtPsePortPwrConsumption(Integer cpeExtPsePortPwrConsumption) {
        this.cpeExtPsePortPwrConsumption = cpeExtPsePortPwrConsumption;
    }

    public Integer getCpeExtPsePortMaxPwrDrawn() {
        return cpeExtPsePortMaxPwrDrawn;
    }

    public void setCpeExtPsePortMaxPwrDrawn(Integer cpeExtPsePortMaxPwrDrawn) {
        this.cpeExtPsePortMaxPwrDrawn = cpeExtPsePortMaxPwrDrawn;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Measurement.class).add("id", id)
                .add("port", port).add("measureTime", measureTime)
                .toString();
    }
}
