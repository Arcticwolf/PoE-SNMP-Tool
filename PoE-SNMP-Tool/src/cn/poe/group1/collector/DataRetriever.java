package cn.poe.group1.collector;

import cn.poe.group1.api.SNMPDataRetriever;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.PortStatus;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * This is a dummy implementation for the SNMPDataRetriever interface and is only
 * for mocking the real behaviour.
 */
public class DataRetriever implements SNMPDataRetriever {
    private Port port;
    private static Logger log = LoggerFactory.getLogger(DataRetriever.class);
    
    public DataRetriever(Port port) {
        this.port = port;
    }

    @Override
    public Measurement takeMeasurement() {
        Measurement measurement = new Measurement();
        measurement.setPort(port);
        //measurement.setCpeExtPsePortDeviceDetected(true); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.3
        measurement.setCpeExtPsePortDeviceDetected(getBoolValue(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.3")));
        //measurement.setCpeExtPsePortEnable(PortStatus.AUTO); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.1
        measurement.setCpeExtPsePortEnable(getPortStatus(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.1")));
        //measurement.setCpeExtPsePortMaxPwrDrawn(1150); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.10
        measurement.setCpeExtPsePortMaxPwrDrawn(Integer.parseInt(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.10")));
        //measurement.setCpeExtPsePortPwrAllocated(1200); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.7
        measurement.setCpeExtPsePortPwrAllocated(Integer.parseInt(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.7")));
        //measurement.setCpeExtPsePortPwrAvailable(1300); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.8
        measurement.setCpeExtPsePortPwrAvailable(Integer.parseInt(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.8")));
        //measurement.setCpeExtPsePortPwrMax(1150); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.6
        measurement.setCpeExtPsePortPwrMax(Integer.parseInt(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.6")));
        //measurement.setCpeExtPsePortPwrConsumption((int) (Math.random() * 1200)); // oid: 1.3.6.1.4.1.9.9.402.1.2.1.9
        measurement.setCpeExtPsePortPwrConsumption(Integer.parseInt(SNMPGet(port.getSw().getIpAddress(), "public", "1.3.6.1.4.1.9.9.402.1.2.1.9")));
        measurement.setMeasureTime(new Date());
        return measurement;
    }
    
    private String SNMPGet(String host, String community, String strOID) {
        String strResponse="";
        Snmp SNMPReq;
        
        try {
            Address Host = new UdpAddress(host+"/"+"161");
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            
            CommunityTarget comtarget = new CommunityTarget();
            comtarget.setCommunity(new OctetString(community));
            comtarget.setVersion(SnmpConstants.version1);
            comtarget.setAddress(Host);
            comtarget.setRetries(2);
            comtarget.setTimeout(5000);
            
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(strOID)));
            pdu.setType(PDU.GET); 
            SNMPReq = new Snmp(transport);
            ResponseEvent response = SNMPReq.get(pdu, comtarget);
            
            if(response != null) {
                if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
                    PDU pduresponse=response.getResponse();
                    strResponse=pduresponse.getVariableBindings().firstElement().toString();
                    if(strResponse.contains("=")) {
                        int len = strResponse.indexOf("=");
                        strResponse=strResponse.substring(len+2, strResponse.length());
                    }
                } else {
                    log.error("No valid SNMP return from: {} for OID: {}", host, strOID);
                }
            } else {
                log.error("A timeout occured from: {}", host);
            }
            SNMPReq.close();
        } catch(Exception e) {
            log.error("Couldn't get SNMP request from: {}", host);
            log.error("StackTrace: {}", e);
        }
        return strResponse;
    }
    
    private Boolean getBoolValue(String str) {
        Boolean ret = false;
        if(str.equals("1")) {
            ret = true;
        } else if(str.equals("0")) {
            ret = false;
        } else {
            log.error("Couldn't convert to bool.");
        }
        return ret;
    }
    
    private PortStatus getPortStatus(String str) {
        PortStatus ps = PortStatus.AUTO;
        if(str.equals("1")) {
            ps = PortStatus.AUTO;
        } else if(str.equals("2")) {
            ps = PortStatus.STATIC;
        } else if(str.equals("3")) {
            ps = PortStatus.LIMIT;
        } else if(str.equals("4")) {
            ps = PortStatus.DISABLE;
        }
        return ps;
    }
}
