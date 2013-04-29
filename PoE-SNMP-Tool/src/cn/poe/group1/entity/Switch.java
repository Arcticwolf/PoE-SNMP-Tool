package cn.poe.group1.entity;

/**
 * This entity models a power-over-ethernet switch which is monitored by this
 * diagnose tool.
 */
public class Switch {
    private String identifier;
    private String ipAddress;
    private String type;
    private int portCount;

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
}
