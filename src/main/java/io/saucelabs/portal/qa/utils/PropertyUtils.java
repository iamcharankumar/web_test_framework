package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.exceptions.TestUtilsException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyUtils {

    public static Properties loadProperties(String fileName) {
        Properties properties;
        InputStream fileInputStream;
        log.info("Loading the Properties file....");
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new TestUtilsException("Properties File failed to load!" + e.getMessage());
        }
        return properties;
    }
}