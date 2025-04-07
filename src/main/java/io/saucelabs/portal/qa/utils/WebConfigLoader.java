package io.saucelabs.portal.qa.utils;

import java.util.Objects;
import java.util.Properties;

public class WebConfigLoader {

    private final Properties PROPERTIES;
    private static WebConfigLoader instance;

    private WebConfigLoader() {
        PROPERTIES = PropertyUtils.loadProperties(System.getProperty("user.dir")
                + "/src/main/java/resources/config.properties");
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
        return getProperty("sauce.labs.url");
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

    private String getProperty(String propertyName) {
        String propertyValue = PROPERTIES.getProperty(propertyName);
        Objects.requireNonNull(propertyValue, "Property not found: " + propertyName);
        return propertyValue;
    }
}