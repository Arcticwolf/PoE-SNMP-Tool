package cn.poe.group1.entity;

import com.google.common.base.Objects;
import java.util.Date;
import javax.persistence.Entity;
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
public class Measurement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="sequence_generator")
    @SequenceGenerator(name="sequence_generator", sequenceName="SEQUENCER")
    private Long id;
    @ManyToOne
    private Switch sw;
    private String oid;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date measureTime;
    private String measuredValue;
    
    public Measurement() {
        // needs to be here because of hibernate
    }
    
    public Measurement(Switch sw, String oid, Date measureTime, String measuredValue) {
        this.sw = sw;
        this.oid = oid;
        this.measureTime = measureTime;
        this.measuredValue = measuredValue;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Switch getSwitch() {
        return sw;
    }

    public void setSwitch(Switch sw) {
        this.sw = sw;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public String getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(String measuredValue) {
        this.measuredValue = measuredValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Measurement.class).add("id", id)
                .add("switch", sw).add("oid", oid).add("measureTime", measureTime)
                .add("measuredValue", measuredValue).omitNullValues().toString();
    }
}
