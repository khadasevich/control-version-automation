package org.cvs.core.config;

import lombok.extern.log4j.Log4j2;
import org.cvs.utilities.ResourceUtility;

import java.util.Properties;

@Log4j2
public class PropertiesReader {

    public static Properties properties;

    public static String getEnvOrConfigVariable(String varName) {
        String value = System.getProperty(varName);
        if (value == null) {
            value = getPropertyFromConfig(varName);
        }
        return value;
    }

    private static String getPropertyFromConfig(String propertyName) {
        return initReader().getProperty(propertyName);
    }

    private static Properties initReader() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(ResourceUtility.getResourceStreamViaName("config.properties"));
            } catch (Exception readException) {
                log.error("Error reading properties file: {}", readException.getMessage());
            }
        }
        return properties;
    }
}
