package cn.poe.group1.gui;

import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;

/**
 *
 */
public class GUIUtils {
    
    public static Date getCurrentDay(int dayOffset) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.add(Calendar.DAY_OF_MONTH, dayOffset);
        return cal.getTime();
    }
    
    public static Date buildDateTime(JDateChooser date, JComboBox hour) {
        Calendar time = Calendar.getInstance();
        time.setTime(date.getDate());
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.add(Calendar.HOUR, 
                    Integer.parseInt(hour.getSelectedItem().toString()));
        return time.getTime();
    }
}
