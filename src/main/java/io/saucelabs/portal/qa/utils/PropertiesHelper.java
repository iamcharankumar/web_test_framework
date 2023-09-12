package io.saucelabs.portal.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private InputStream fileInputStream;
    private Properties properties;

    private Properties loadProperties(String fileName) {
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getProperty(String fileName, String property) {
        Properties hostProperties = new Properties();
        try {
            hostProperties = loadProperties(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostProperties.getProperty(property);
    }
}
