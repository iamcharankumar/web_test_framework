package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;

import java.util.Objects;
import java.util.Properties;

public class WebConfigLoader {

    private final Properties PROPERTIES;
    private static WebConfigLoader instance;

    private WebConfigLoader() {
        PROPERTIES = PropertiesHelper.loadProperties(SauceLabsPortalConstants.SAUCELABS_PORTAL_PROPERTIES_FILE);
    }

    public static WebConfigLoader getInstance() {
        if (instance == null) {
            synchronized (WebConfigLoader.class) {
                if (instance == null) {
                    instance = new WebConfigLoader();
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