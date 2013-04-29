package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import java.util.ResourceBundle;

/**
 * This class bundles the logic of retrieving data from the configuration 
 * properties file.
 */
public class PropertyFileConfig implements Configuration {
    private static final String PROPERTY_FILE_NAME = "config";
    private static final String MEASUREMENT_INTERVAL = "measurement.interval";
    private final ResourceBundle bundle;
    
    public PropertyFileConfig() {
        this.bundle = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
    }

    @Override
    public int getMeasurementInterval() {
        return getIntValue(MEASUREMENT_INTERVAL, 1000);
    }
    
    private int getIntValue(String name, int defaultValue) {
        try {
            String value = bundle.getString(name);
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.err.print("Unable to retrieve the value with the key ");
            System.err.printf("%s, the default value %d is taken instead.\n", 
                    name, defaultValue);
            e.printStackTrace();
            return defaultValue;
        }
    }
}
