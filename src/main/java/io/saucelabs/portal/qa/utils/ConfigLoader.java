package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.DiscordException;
import io.saucelabs.portal.qa.exceptions.SauceLabsPortalException;

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
        String sauceLabsPortalUrl = PROPERTIES.getProperty("saucelabs.url");
        if (sauceLabsPortalUrl != null)
            return sauceLabsPortalUrl;
        else
            throw new SauceLabsPortalException("Sauce Labs Portal URL is null!");
    }

    public String getSauceLabsPortalUserName() {
        String sauceLabsPortalUserName = PROPERTIES.getProperty("username");
        if (sauceLabsPortalUserName != null)
            return sauceLabsPortalUserName;
        else
            throw new SauceLabsPortalException("Sauce Labs Portal Username is null!");
    }

    public String getSauceLabsPortalPassword() {
        String sauceLabsPortalPassword = PROPERTIES.getProperty("password");
        if (sauceLabsPortalPassword != null)
            return sauceLabsPortalPassword;
        else
            throw new SauceLabsPortalException("Sauce Labs Portal Password is null!");
    }

    public String getRunMode() {
        String runMode = PROPERTIES.getProperty("runmode");
        if (runMode != null)
            return runMode;
        else
            throw new SauceLabsPortalException("Run Mode is null!");
    }

    public String getBrowser() {
        String browser = PROPERTIES.getProperty("browser");
        if (browser != null)
            return browser;
        else
            throw new SauceLabsPortalException("Browser is null!");
    }

    public String getServerUrl() {
        String serverUrl = PROPERTIES.getProperty("server");
        if (serverUrl != null)
            return serverUrl;
        else
            throw new SauceLabsPortalException("Server URL is null!");
    }

    public String getDiscordUrl() {
        String discordUrl = PROPERTIES.getProperty("discord.url");
        if (discordUrl != null)
            return discordUrl;
        else
            throw new DiscordException("Discord URL failed to load from the properties file!");
    }
}