package com.stchatbot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebhookConfig implements ApplicationRunner {

    private final RestTemplate restTemplate;
    private String token = "512fc36a9467e3c8-83dc1ff986f69070-ecdb66a74e9c42c6";
    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
    private String payload = "{\"url\":\"https://4f68-1-227-43-68.ngrok-free.app\"}";
    private String payload1 = "{\"url\":\"https://stchatbot.site\"}";
    @Override
    public void run(ApplicationArguments args) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Viber-Auth-Token", token);

        HttpEntity<String> httpEntity = new HttpEntity<>(payload1, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);

        log.info(responseEntity.getBody());
    }
}
