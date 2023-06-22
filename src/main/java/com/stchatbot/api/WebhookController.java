package com.stchatbot.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class WebhookController {
    @GetMapping("/health")
    public String health() {
        return "health check";
    }
    @PostMapping("/api")
    public void webhook1(@RequestBody WebhookCallbackData webhookCallbackData) {
        log.info("post");
        log.info(webhookCallbackData.getEventType());
    }
}