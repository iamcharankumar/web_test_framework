package io.saucelabs.portal.qa.services.discord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscordHelper {

    private static DiscordController discordController;

    public static DiscordController getDiscordController() {
        if (discordController == null) {
            log.info("Setting up the Discord Controller...");
            discordController = new DiscordController();
        }
        return discordController;
    }
}