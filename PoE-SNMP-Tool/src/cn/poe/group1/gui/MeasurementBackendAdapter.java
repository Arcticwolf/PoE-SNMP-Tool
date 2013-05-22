/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.poe.group1.gui;

import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sauron
 */
public class MeasurementBackendAdapter implements MeasurementBackend
{

    @Override
    public void saveMeasurement(Measurement measurement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> queryMeasurementsBySwitch(Switch sw, Date startTime, Date endTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> queryMeasurementsByPort(Port port, Date startTime, Date endTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> queryMeasurementsBySwitch(Switch sw) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> queryMeasurementsByPort(Port port) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persistSwitch(Switch sw) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteSwitch(Switch sw) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Switch getSwitchById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Switch> retrieveAllSwitches() {
        return DataStub.getSwitchList();
    }

    @Override
    public void persistPort(Port port) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletePort(Port port) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Port getPortById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Port> retrieveAllPorts(Switch sw) {
        if( sw.getIdentifier() == "Switch1") 
            return DataStub.getPortList();
        else
            return null;
    }
    
    @Override
    public List<PortData> retrieveAllPortData(Switch sw, Date startTime, Date endTime)
    {
        List<PortData> tmp = PortData.createPortDataList( this.retrieveAllPorts(sw));
        
        for(PortData pd : tmp)
        {
            pd.setMeasurementList( DataStub.getMeasurementList(pd.getPort()));                        
        }
        
        return tmp;        
    }
}
