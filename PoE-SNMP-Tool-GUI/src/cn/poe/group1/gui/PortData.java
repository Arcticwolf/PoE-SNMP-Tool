/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.poe.group1.gui;

import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sauron
 */
public class PortData {

    private Port port;
    private List<Measurement> measurementList;
    private Integer avgCpeExtPsePortPwrMax; // milliwatts
    private Integer avgCpeExtPsePortPwrAllocated; // milliwatts
    private Integer avgCpeExtPsePortPwrAvailable; // milliwatts
    private Integer avgCpeExtPsePortPwrConsumption; // milliwatts
    private Integer avgCpeExtPsePortMaxPwrDrawn; // milliwatts

    public PortData()
    {
        this.measurementList = new LinkedList<Measurement>();
        this.avgCpeExtPsePortMaxPwrDrawn = 0;
        this.avgCpeExtPsePortPwrAllocated = 0;
        this.avgCpeExtPsePortPwrAvailable = 0;
        this.avgCpeExtPsePortPwrConsumption = 0;
        this.avgCpeExtPsePortPwrMax = 0;
    }
    
    public static List<PortData> createPortDataList(List<Port> portList)
    {
        List<PortData> pdList = new LinkedList<PortData>();
        if(portList == null)
            return pdList;
        
        
        PortData pd = null;        
        for(Port p : portList)
        {
            pd = new PortData();
            pd.setPort(p);
            pdList.add(pd);
        }
        
        return pdList;
    }
    
    private void calcPortData()
    {
        this.avgCpeExtPsePortMaxPwrDrawn = 0;
        this.avgCpeExtPsePortPwrAllocated = 0;
        this.avgCpeExtPsePortPwrAvailable = 0;
        this.avgCpeExtPsePortPwrConsumption = 0;
        this.avgCpeExtPsePortPwrMax = 0;
        
        for( Measurement m : measurementList)
        {

            if( m.getCpeExtPsePortMaxPwrDrawn() != null)
                this.avgCpeExtPsePortMaxPwrDrawn += m.getCpeExtPsePortMaxPwrDrawn();
            
            if( m.getCpeExtPsePortPwrAllocated() != null)
                this.avgCpeExtPsePortPwrAllocated += m.getCpeExtPsePortPwrAllocated();
            
            if( m.getCpeExtPsePortPwrAvailable() != null)
                this.avgCpeExtPsePortPwrAvailable += m.getCpeExtPsePortPwrAvailable();
            
            if( m.getCpeExtPsePortPwrConsumption() != null)
                this.avgCpeExtPsePortPwrConsumption += m.getCpeExtPsePortPwrConsumption();
            
            if( m.getCpeExtPsePortPwrMax() != null)
                this.avgCpeExtPsePortPwrMax += m.getCpeExtPsePortPwrMax();
        }
        
        if( (measurementList != null) && (measurementList.size() > 0) )
        {
            this.avgCpeExtPsePortMaxPwrDrawn /= measurementList.size();
            this.avgCpeExtPsePortPwrAllocated /= measurementList.size();
            this.avgCpeExtPsePortPwrAvailable /= measurementList.size();
            this.avgCpeExtPsePortPwrConsumption /= measurementList.size();
            this.avgCpeExtPsePortPwrMax /= measurementList.size();
        }
    }
    
    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
        this.calcPortData();
    }

    public Integer getAvgCpeExtPsePortPwrMax() {
        return avgCpeExtPsePortPwrMax;
    }

    public void setAvgCpeExtPsePortPwrMax(Integer avgCpeExtPsePortPwrMax) {
        this.avgCpeExtPsePortPwrMax = avgCpeExtPsePortPwrMax;
    }

    public Integer getAvgCpeExtPsePortPwrAllocated() {
        return avgCpeExtPsePortPwrAllocated;
    }

    public void setAvgCpeExtPsePortPwrAllocated(Integer avgCpeExtPsePortPwrAllocated) {
        this.avgCpeExtPsePortPwrAllocated = avgCpeExtPsePortPwrAllocated;
    }

    public Integer getAvgCpeExtPsePortPwrAvailable() {
        return avgCpeExtPsePortPwrAvailable;
    }

    public void setAvgCpeExtPsePortPwrAvailable(Integer avgCpeExtPsePortPwrAvailable) {
        this.avgCpeExtPsePortPwrAvailable = avgCpeExtPsePortPwrAvailable;
    }

    public Integer getAvgCpeExtPsePortPwrConsumption() {
        return avgCpeExtPsePortPwrConsumption;
    }

    public void setAvgCpeExtPsePortPwrConsumption(Integer avgCpeExtPsePortPwrConsumption) {
        this.avgCpeExtPsePortPwrConsumption = avgCpeExtPsePortPwrConsumption;
    }

    public Integer getAvgCpeExtPsePortMaxPwrDrawn() {
        return avgCpeExtPsePortMaxPwrDrawn;
    }

    public void setAvgCpeExtPsePortMaxPwrDrawn(Integer avgCpeExtPsePortMaxPwrDrawn) {
        this.avgCpeExtPsePortMaxPwrDrawn = avgCpeExtPsePortMaxPwrDrawn;
    }
}
