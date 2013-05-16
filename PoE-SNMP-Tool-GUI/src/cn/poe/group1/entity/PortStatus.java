package cn.poe.group1.entity;

/**
 * The port status enumeration is a representation of the status a port can 
 * have according to Cisco.
 */
public enum PortStatus {
    AUTO(1), STATIC(2), LIMIT(3), DISABLE(4);
    
    private int value;    

    private PortStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
