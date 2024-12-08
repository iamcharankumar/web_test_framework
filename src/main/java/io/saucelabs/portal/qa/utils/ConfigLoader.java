package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;

import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {

    private final Properties PROPERTIES;
    private static ConfigLoader instance;

    private ConfigLoader() {
        PROPERTIES = PropertyUtils.loadProperties(SauceLabsPortalConstants.SAUCELABS_PORTAL_PROPERTIES_FILE);
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
        return getPropertyValue("saucelabs.url");
    }

    public String getSauceLabsPortalUserName() {
        return getPropertyValue("username");
    }

    public String getSauceLabsPortalPassword() {
        return getPropertyValue("password");
    }

    public String getServerUrl() {
        return getPropertyValue("server");
    }

    public String getDiscordUrl() {
        return getPropertyValue("discord.url");
    }

    private String getPropertyValue(String propertyKey) {
        String propertyValue = PROPERTIES.getProperty(propertyKey);
        Objects.requireNonNull(propertyKey, "Property Value for Property Key: " + propertyKey + " is empty!");
        return propertyValue;
    }
}