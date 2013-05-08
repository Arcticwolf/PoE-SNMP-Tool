package cn.poe.group1.db;

import cn.poe.group1.api.MeasurementBackend;
import cn.poe.group1.entity.Measurement;
import cn.poe.group1.entity.Switch;
import java.util.Calendar;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a JUnit test class for the measurement database implementation.
 */
public class MeasurementBackendTest {
    private static final String FACTORY_NAME = "poe-snmp-tool-test";
    private static MeasurementBackend backend;
    
    @BeforeClass
    public static void init() {
        backend = new MeasurementDatabase(FACTORY_NAME);
    }

    @Test
    public void testSaveSwitch_shouldInsertSwitch() throws Exception {
        int size_before = backend.retrieveAllSwitches().size();
        backend.persistSwitch(new Switch("test1/id", "testIp", "testtype", 20));
        backend.persistSwitch(new Switch("test1/id2", "testIp2", "testtype2", 30));
        
        int size_after = backend.retrieveAllSwitches().size();
        assertThat(size_after, is(size_before + 2));
    }
    
    @Test
    public void testUpdateSwitch_shouldUpdateSwitch() throws Exception {
        Switch sw = new Switch("test2/id", "testIp", "testtype", 20);
        backend.persistSwitch(sw);
        sw = backend.getSwitchById("test2/id");
        assertThat(sw, notNullValue());
        assertThat(sw.getIdentifier(), is("test2/id"));
        assertThat(sw.getType(), is("testtype"));
        
        sw.setType("otherType");
        backend.persistSwitch(sw);
        sw = backend.getSwitchById("test2/id");
        assertThat(sw, notNullValue());
        assertThat(sw.getIdentifier(), is("test2/id"));
        assertThat(sw.getType(), is("otherType"));        
    }
    
    @Test
    public void testLoadMeasurementsWithIncorrectSwitchId_shouldReturnNoResult() throws Exception {
        Switch sw = new Switch("test3/id", "testIp", "testtype", 20);
        backend.persistSwitch(sw);
        
        Calendar begin = getBeginTime();
        Calendar end = getEndTime();
        
        insertTestMeasurements(sw);
        
        List<Measurement> results = backend.queryMeasurements("nonsenseid", "oid1", 
                begin.getTime(), end.getTime());
        
        assertThat(results.size(), is(0));
    }
    
    @Test
    public void testLoadMeasurementsWithIncorrectOId_shouldReturnNoResult() throws Exception {
        Switch sw = new Switch("test4/id", "testIp", "testtype", 20);
        backend.persistSwitch(sw);
        
        Calendar begin = getBeginTime();
        Calendar end = getEndTime();
        
        insertTestMeasurements(sw);
        
        List<Measurement> results = backend.queryMeasurements("test4/id", "nonsenseoid", 
                begin.getTime(), end.getTime());
        assertThat(results.size(), is(0));
    }
    
    @Test
    public void testLoadMeasurementsWithIncorrectTimes_shouldReturnNoResult() throws Exception {
        Switch sw = new Switch("test5/id", "testIp", "testtype", 20);
        backend.persistSwitch(sw);
        
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, 1);
        
        insertTestMeasurements(sw);
        
        List<Measurement> results = backend.queryMeasurements("test5/id", "oid1", 
                begin.getTime(), end.getTime());
        assertThat(results.size(), is(0));
    }
    
    @Test
    public void testLoadMeasurements_shouldLoadResult() throws Exception {
        Switch sw = new Switch("test6/id", "testIp", "testtype", 20);
        backend.persistSwitch(sw);
        
        Calendar begin = getBeginTime();
        Calendar end = getEndTime();
        
        insertTestMeasurements(sw);
        
        List<Measurement> results = backend.queryMeasurements("test6/id", "oid1", 
                begin.getTime(), end.getTime());
        assertThat(results.size(), is(3));
        
        results = backend.queryMeasurements("test6/id", "oid2", begin.getTime(), 
                end.getTime());
        assertThat(results.size(), is(3));
        
        begin.add(Calendar.MINUTE, 1);
        begin.add(Calendar.SECOND, 30);
        results = backend.queryMeasurements("test6/id", "oid1", begin.getTime(), 
                end.getTime());
        assertThat(results.size(), is(2));
    }
    
    private Calendar getBeginTime() {
        Calendar begin = Calendar.getInstance();
        begin.set(2013, 5, 8, 10, 29, 0);
        return begin;
    }
    
    private Calendar getEndTime() {
        Calendar end = Calendar.getInstance();
        end.set(2013, 5, 8, 10, 33, 0);
        return end;
    }
    
    private void insertTestMeasurements(Switch sw) throws Exception {
        Calendar c = getBeginTime();
        c.set(Calendar.MINUTE, 30);
        
        backend.saveMeasurement(new Measurement(sw, "oid1", c.getTime(), "1"));
        backend.saveMeasurement(new Measurement(sw, "oid2", c.getTime(), "1"));
        
        c.set(Calendar.MINUTE, 31);
        backend.saveMeasurement(new Measurement(sw, "oid1", c.getTime(), "2"));
        backend.saveMeasurement(new Measurement(sw, "oid2", c.getTime(), "2"));
        
        c.set(Calendar.MINUTE, 32);
        backend.saveMeasurement(new Measurement(sw, "oid1", c.getTime(), "3"));
        backend.saveMeasurement(new Measurement(sw, "oid2", c.getTime(), "3"));
    }
}
