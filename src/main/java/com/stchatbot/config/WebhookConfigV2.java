package com.stchatbot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WebhookConfigV2 {
    private String token = "512fc36a9467e3c8-83dc1ff986f69070-ecdb66a74e9c42c6";
    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
    private String url = "https://stchatbot.site";
    //private String url = "https://552a-1-227-43-68.ngrok-free.app";
    //String body1 = "{\"url\":\"https://552a-1-227-43-68.ngrok-free.app/api\",\"event_type\":[\"delivered\"]|}";

    @EventListener(ApplicationReadyEvent.class)
    public void setwebhook(ApplicationReadyEvent event) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        String body = objectMapper.writeValueAsString(createWebHookParams());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Viber-Auth-Token", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);

        log.info("Viber Webhook status {}, body {}", response.getStatusCode(), response.getBody());
    }

    private Map<String, Object> createWebHookParams() {
        return Map.of(
                "url", url,
                "send_name", true,
                "send_photo", true,
                "event_types", getEvents()
        );
    }
    private List<String> getEvents() {
        return List.of("delivered", "seen", "failed", "subscribed", "unsubscribed", "conversation_started", "message");
    }
}