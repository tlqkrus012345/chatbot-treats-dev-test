package com.stchatbot.api;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
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
    @PostMapping("/")
    public void webhook1(@RequestBody String callback) {
        log.info("post");
        JSONObject jsonObject = new JSONObject(callback);
        String eventType = jsonObject.getString("event");
        if (eventType.equals("conversation_started")) {
            // user의 name을 추출
            JSONObject user = jsonObject.getJSONObject("user");
            String name = user.getString("name");
            log.info(name);
        }

    }
    //@GetMapping("/")
    public void webhook2(@RequestBody WebhookCallbackData webhookCallbackData) {
        log.info("get");
        log.info(webhookCallbackData.getEventType());
    }
}