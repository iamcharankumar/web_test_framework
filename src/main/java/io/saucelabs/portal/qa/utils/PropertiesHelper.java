package io.saucelabs.portal.qa.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesHelper {

    public static Properties loadProperties(String fileName) {
        Properties properties;
        InputStream fileInputStream;
        log.info("Loading the Properties file....");
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Properties File failed to load!" + e.getMessage());
        }
        return properties;
    }
}