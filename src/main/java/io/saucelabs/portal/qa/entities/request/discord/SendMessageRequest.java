package io.saucelabs.portal.qa.entities.request.discord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SendMessageRequest {
    private String content;
}