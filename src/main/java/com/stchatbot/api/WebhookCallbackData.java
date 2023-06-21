package com.stchatbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebhookCallbackData {

    @JsonProperty("event")
    private String eventType;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("message_token")
    private long messageToken;
}
