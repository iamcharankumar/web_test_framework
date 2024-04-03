package io.saucelabs.portal.qa.services.discord;

import io.restassured.response.Response;
import io.saucelabs.portal.qa.entities.request.discord.SendMessageRequest;
import io.saucelabs.portal.qa.utils.RetryUtils;
import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;

@Slf4j
public class DiscordController {

    DiscordClient discordClient;

    public DiscordController() {
        this.discordClient = new DiscordClient();
    }

    public String getSendMessageResponse(SendMessageRequest sendMessageRequest) {
        return Failsafe.with(new RetryUtils().getRetryPolicyForDiscordException(2, 3)).get(() -> {
            Response sendMessageResponse = discordClient.getSendMessageResponse(sendMessageRequest);
            if (sendMessageResponse.getStatusCode() != 204)
                log.error("Retrying for the Discord Send Message Code. Please stay with us...");
            return sendMessageResponse.asString();
        });
    }
}