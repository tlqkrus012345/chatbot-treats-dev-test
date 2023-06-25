package com.stchatbot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
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
@RequiredArgsConstructor
public class WebhookConfigV2 {
    private String token = "513b21592f67e244-924f73242a636229-b8f631acb00784c";
    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
    private String payload = "https://stchatbot.site";
    private String payload1 = "https://4f68-1-227-43-68.ngrok-free.app";
    String body1 = "{\"url\":\"https://552a-1-227-43-68.ngrok-free.app/api\",\"event_type\":[\"delivered\"]|}";

    private final RestTemplateConfig restTemplateConfig;
    @EventListener(ApplicationReadyEvent.class)
    public void setwebhook(ApplicationReadyEvent event) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(createWebHookParams());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Viber-Auth-Token", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplateConfig.restTemplate().exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);

        log.info("Viber Webhook status {}, body {}", response.getStatusCode(), response.getBody());

    }

    private Map<String, Object> createWebHookParams() {
        return Map.of(
                "url", payload,
                "send_name", true,
                "send_photo", true,
                "event_types", getEvents()
        );
    }
    private List<String> getEvents() {
        return List.of("delivered", "seen", "failed", "subscribed", "unsubscribed", "conversation_started", "message");
    }
}
