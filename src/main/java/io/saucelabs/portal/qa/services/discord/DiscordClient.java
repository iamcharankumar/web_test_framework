package io.saucelabs.portal.qa.services.discord;

import io.restassured.response.Response;
import io.saucelabs.portal.qa.commons.rest.ApiConstants;
import io.saucelabs.portal.qa.commons.rest.RestResource;
import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.entities.request.discord.SendMessageRequest;
import io.saucelabs.portal.qa.exceptions.DiscordException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public class DiscordClient {

    @SneakyThrows
    public Response getSendMessageResponse(SendMessageRequest sendMessageRequest) {
        String discordWebHookEndPoint = SauceLabsPortalConstants.DISCORD_WEBHOOK + ApiConstants.WEBHOOK_TOKEN;
        String discordMessage = RestResource.getInstance().serialize(sendMessageRequest);
        Response sendMessageResponse = RestResource.getInstance().postApiResponse(discordMessage, discordWebHookEndPoint);
        if (sendMessageResponse != null)
            return sendMessageResponse;
        else
            throw new DiscordException("Client Exception: Discord Send Message API");
    }
}