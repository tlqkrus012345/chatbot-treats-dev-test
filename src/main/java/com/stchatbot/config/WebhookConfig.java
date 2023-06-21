package com.stchatbot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class WebhookConfig {
//
//    private final RestTemplate restTemplate;
//
//    private String token = "512fc36a9467e3c8-83dc1ff986f69070-ecdb66a74e9c42c6";
//    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
//    private String payload = "{\"url\":\"https://stchatbot.site/webhook\"}";
//    @PostConstruct
//    public void setWebhook() {
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("X-Viber-Auth-Token", token);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            log.info("Webhook Response: {}", responseEntity.getBody());
//        } else {
//            log.info("Webhook Fail");
//        }
//    }
//
//
//}
