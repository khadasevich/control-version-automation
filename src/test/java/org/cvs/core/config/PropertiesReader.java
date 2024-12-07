package org.cvs.core.config;

import org.cvs.utilities.ResourceUtility;

import java.util.Properties;

public class PropertiesReader {

    public static Properties properties;

    private static String getPropertyFromConfig(String property) {
        return initReader().getProperty(property);
    }

    private static Properties initReader() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(ResourceUtility.getResourceStreamViaName("config.properties"));
            } catch (Exception e) {
                //ToDo: Add logger
            }
        }
        return properties;
    }
}
