package io.saucelabs.portal.qa.utils;

import io.saucelabs.portal.qa.entities.request.discord.SendMessageRequest;
import io.saucelabs.portal.qa.exceptions.DiscordException;
import io.saucelabs.portal.qa.services.discord.DiscordHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscordUtils {

    public static String buildDiscordMessage(int passedTestCases, int failedTestCases, int skippedTestCases, int totalTestCases) {
        StringBuilder discordMessageBuilder = new StringBuilder();
        discordMessageBuilder.append("\n******************************\n")
                .append("\nPASS: ").append(passedTestCases)
                .append("\nFAIL: ").append(failedTestCases)
                .append("\nSKIP: ").append(skippedTestCases)
                .append("\nTOTAL: ").append(totalTestCases).append("\n")
                .append("\nPASS% : ").append(TestUtils.calculateTestCasePercentage(passedTestCases, totalTestCases))
                .append("\nFAIL% : ").append(TestUtils.calculateTestCasePercentage(failedTestCases, totalTestCases))
                .append("\nReport Portal Run: ").append(TestUtils.getReportPortalLaunchUrl())
                .append("\n******************************\n");
        return String.valueOf(discordMessageBuilder);
    }

    public static void sendMessageToChannel(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(message);
        String sendMessageResponse = DiscordHelper.getDiscordController().getSendMessageResponse(sendMessageRequest);
        if (sendMessageResponse.isEmpty())
            return;
        else
            throw new DiscordException("DISCORD UTILS: Send Message Failed!");
    }
}
