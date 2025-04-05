package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;

import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {

    private final Properties PROPERTIES;
    private static ConfigLoader instance;

    private ConfigLoader() {
        PROPERTIES = PropertiesHelper.loadProperties(SauceLabsPortalConstants.SAUCELABS_PORTAL_PROPERTIES_FILE);
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }
        return instance;
    }

    public String getSauceLabsPortalUrl() {
        return getProperty("saucelabs.url");
    }

    public String getSauceLabsPortalUserName() {
        return getProperty("username");
    }

    public String getSauceLabsPortalPassword() {
        return getProperty("password");
    }

    public String getServerUrl() {
        return getProperty("server");
    }

    public String getDiscordUrl() {
        return getProperty("discord.url");
    }

    private String getProperty(String propertyName) {
        String propertyValue = PROPERTIES.getProperty(propertyName);
        Objects.requireNonNull(propertyValue, "Property not found: " + propertyName);
        return propertyValue;
    }
}