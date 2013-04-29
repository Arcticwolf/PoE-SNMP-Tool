package cn.poe.group1.entity;

import java.util.Date;

/**
 * This is the model of a measurement retrieved from a SNMP switch.
 */
public class Measurement {
    private Switch sw;
    private String oid;
    private Date timestamp;
    private String value;

    public Switch getSwitch() {
        return sw;
    }

    public void setSwitch(Switch sw) {
        this.sw = sw;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
