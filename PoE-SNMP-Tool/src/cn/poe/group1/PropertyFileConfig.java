package cn.poe.group1;

import cn.poe.group1.api.Configuration;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class bundles the logic of retrieving data from the configuration 
 * properties file.
 */
public class PropertyFileConfig implements Configuration {
    private static Logger log = LoggerFactory.getLogger(PropertyFileConfig.class);
    private static final String PROPERTY_FILE_NAME = "config";
    private static final String MEASUREMENT_INTERVAL = "measurement.interval";
    private static final String DATA_RETRIEVER_IMPL = "data.retriever.impl";
    private final ResourceBundle bundle;
    
    public PropertyFileConfig() {
        this.bundle = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
    }

    @Override
    public int getMeasurementInterval() {
        return getIntValue(MEASUREMENT_INTERVAL, 1000);
    }
    
    @Override
    public String getDataRetrieverImpl() {
        return getStringValue(DATA_RETRIEVER_IMPL, "cn.poe.group1.collector.DummyDataRetriever");
    }
    
    private String getStringValue(String name, String defaultValue) {
        try {
            return bundle.getString(name);
        } catch (Exception e) {
            log.error("Unable to retrieve the value with the key "
                    + "{}, the default value {} is taken instead.", 
                    new Object[] {name, defaultValue});
            log.error("StackTrace:", e);
            return defaultValue;
        }
    }
    
    private int getIntValue(String name, int defaultValue) {
        try {
            String value = bundle.getString(name);
            return Integer.parseInt(value);
        } catch (Exception e) {
            log.error("Unable to retrieve the value with the key "
                    + "{}, the default value {} is taken instead.", 
                    new Object[] {name, defaultValue});
            log.error("StackTrace:", e);
            return defaultValue;
        }
    }
}
