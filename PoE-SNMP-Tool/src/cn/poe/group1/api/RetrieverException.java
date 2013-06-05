package cn.poe.group1.api;

/**
 * The RetrieverException is thrown if during the retrieving of SNMP values an
 * error occurs.
 */
public class RetrieverException extends Exception {

    public RetrieverException() {
        super();
    }
    
    public RetrieverException(String cause) {
        super(cause);
    }
    
    public RetrieverException(Exception reason) {
        super(reason);
    }
    
    public RetrieverException(String cause, Exception reason) {
        super(cause, reason);
    }
}
