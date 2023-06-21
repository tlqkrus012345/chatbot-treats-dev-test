package com.stchatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    public CloseableHttpClient httpClient() {
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(100); // 전체 최대 연결 수
//        connectionManager.setDefaultMaxPerRoute(20); // 호스트당 최대 연결 수
//
//        return HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .build();
//    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
