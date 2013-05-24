package cn.poe.group1.gui;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;

/**
 * The GUIUtils class separates some functionality that is not directly related
 * to the GUI in an own file.
 */
public class GUIUtils {
    
    /**
     * Gets the current day + offset days.
     * @param dayOffset the amount of days that should be added.
     * @return The current Date + dayOffset days.
     */
    public static Date getCurrentDay(int dayOffset) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.add(Calendar.DAY_OF_MONTH, dayOffset);
        return cal.getTime();
    }
    
    /**
     * Creates a Date object out of the JDateChooser and a JComboBox representing
     * the hour.
     * @param date A JDateChooser holding the date.
     * @param hour A JComboBox holding the hour.
     * @return The resulting Date object.
     */
    public static Date buildDateTime(JDateChooser date, JComboBox hour, JSpinField minute) {
        Calendar time = Calendar.getInstance();
        time.setTime(date.getDate());
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.add(Calendar.HOUR, 
                    Integer.parseInt(hour.getSelectedItem().toString()));
        time.add(Calendar.MINUTE,
                minute.getValue());
        return time.getTime();
    }
}
