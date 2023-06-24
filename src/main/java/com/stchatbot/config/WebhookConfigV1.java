package com.stchatbot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import javax.annotation.PostConstruct;
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class WebhookConfigV1 {
//
//    private final RestTemplate restTemplate;
//    private String token = "512fc36a9467e3c8-83dc1ff986f69070-ecdb66a74e9c42c6";
//    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
//    private String payload = "{\"url\":\"https://4f68-1-227-43-68.ngrok-free.app\"}";
//    private String payload1 = "{\"url\":\"https://stchatbot.site\"}";
//
//    @PostConstruct
//    public void setWebhook() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.set("X-Viber-Auth-Token", token);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);
//
//        log.info("response {}", responseEntity.getBody());
//    }
//}
