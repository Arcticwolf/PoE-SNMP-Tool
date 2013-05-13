package cn.poe.group1.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity models a power-over-ethernet switch which is monitored by this
 * diagnose tool.
 */
@Entity
@Table( name = "SWITCH" )
public class Switch implements Serializable {
    @Id
    private String identifier;
    private String ipAddress;
    private String type;
    private int portCount;
    private String comment;
    
    public Switch() {
        // needs to be here because of hibernate
    }
    
    public Switch(String identifier, String ipAddress, String type, int portCount,
            String comment) {
        this.identifier = identifier;
        this.ipAddress = ipAddress;
        this.type = type;
        this.portCount = portCount;
        this.comment = comment;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPortCount() {
        return portCount;
    }

    public void setPortCount(int portCount) {
        this.portCount = portCount;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Switch.class).add("identifier", identifier)
                .add("ipAddress", ipAddress).add("type", type)
                .add("portCount", portCount).add("comment", comment)
                .omitNullValues().toString();
    }
}
