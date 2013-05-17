/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.poe.group1.gui;

import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Port;
import cn.poe.group1.entity.Switch;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sauron
 */
public class DataStub {
    
    public static List<Switch> getSwitchList()
    {
        List<Switch> tmp = new LinkedList<Switch>();
        tmp.add( new Switch("Switch1", "192.168.0.1", "type01", 24, ""));
        tmp.add( new Switch("Switch2", "192.168.0.2", "type01", 24, ""));
        tmp.add( new Switch("Switch3", "192.168.0.3", "type02", 48, ""));
        tmp.add( new Switch("Switch4", "192.168.0.4", "type02", 48, ""));        
        return tmp;
    }
    
    public static List<Port> getPortList()
    {
        List<Port> tmp = new LinkedList<Port>();
        Switch tmpSw = new Switch("Switch1", "192.168.0.1", "type01", 24, "");        
        tmp.add( new Port(tmpSw, 1, ""));
        tmp.add( new Port(tmpSw, 2, ""));
        tmp.add( new Port(tmpSw, 3, ""));
        tmp.add( new Port(tmpSw, 4, ""));
        return tmp;
    }
    
    public static List<Measurement> getMeasurementList(Port port)
    {
        List<Measurement> tmp = new LinkedList<Measurement>();
        Measurement m = new Measurement();

        for(int i = 0; i < 10; i++)
        {
            m = new Measurement();
            m.setPort(port);
            m.setCpeExtPsePortPwrConsumption( 200 + DataStub.randomInteger() );
            m.setCpeExtPsePortPwrMax( 500 + DataStub.randomInteger() );
            tmp.add(m);
        }
        
        return tmp;
    }
    
    public static int randomInteger()
    {
        Double d = Math.random() * 100;
        return d.intValue();        
    }
}
