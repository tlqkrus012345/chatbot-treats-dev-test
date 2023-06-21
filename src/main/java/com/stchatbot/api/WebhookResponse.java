package com.stchatbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebhookResponse {
    @JsonProperty("status")
    private int status;
    @JsonProperty("status_message")
    private String statusMessage;
    @JsonProperty("event_types")
    private List<String> eventTypes;
}
