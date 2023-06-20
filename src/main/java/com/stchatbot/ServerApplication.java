package com.stchatbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class ServerApplication {


	public static void sendWebhook(String webhookUrl, String token, String payload) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-Viber-Auth-Token", token);

		HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);


		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			log.info("Webhook Success");
			log.info("Webhook Response: {}", responseEntity.getBody());
		} else {
			log.info("Webhook Fail");
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

		String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
		String token = "512fc36a9467e3c8-83dc1ff986f69070-ecdb66a74e9c42c6";
		String payload = "{\"url\":\"https://stchatbot.site/webhook\"}";

		sendWebhook(webhookUrl, token, payload);
	}

}
