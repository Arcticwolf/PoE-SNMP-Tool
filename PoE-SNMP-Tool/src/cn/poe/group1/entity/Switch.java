package cn.poe.group1.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String community;
    @OneToMany(mappedBy="sw", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Port> ports;
    
    public Switch() {
        // needs to be here because of hibernate
    }
    
    public Switch(String identifier, String ipAddress, String type, int portCount,
            String comment, String community) {
        this.identifier = identifier;
        this.ipAddress = ipAddress;
        this.type = type;
        this.portCount = portCount;
        this.comment = comment;
        this.community = community;
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

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
    
    public void addPort(Port port) {
        if (this.ports == null) {
            this.ports = new ArrayList<Port>(); 
        }
        this.ports.add(port);
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getCommunity() {
        return this.community;
    }
    
    public void setCommunity(String community) {
        this.community = community;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Switch.class).add("identifier", identifier)
                .add("ipAddress", ipAddress).add("type", type)
                .add("portCount", portCount).add("comment", comment)
                .add("community", community)
                .omitNullValues().toString();
    }
}
