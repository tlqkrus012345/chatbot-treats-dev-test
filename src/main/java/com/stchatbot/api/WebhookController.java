package com.stchatbot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    @GetMapping("/health")
    public String health() {
        return "health check!!";
    }
    @PostMapping("/webhook")
    public WebhookCallbackData webhook(@RequestBody WebhookCallbackData webhookCallbackData) {
        return webhookCallbackData;
    }
}