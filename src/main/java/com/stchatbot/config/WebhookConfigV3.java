package com.stchatbot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebhookConfigV3 implements ServletContextInitializer {

    private final RestTemplateConfig restTemplateConfig;
    private String token = "513b21592f67e244-924f73242a636229-b8f631acb00784c";
    private String webhookUrl = "https://chatapi.viber.com/pa/set_webhook";
    private String payload = "https://stchatbot.site";

    @SneakyThrows
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");


//        registration.setInitParameter("dispatchOptionsRequest", "true"); // OPTIONS 요청 허용
//        registration.addMapping("/");

        sendWebhookRequest();
    }

    private void sendWebhookRequest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(createWebHookParams());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Viber-Auth-Token", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplateConfig.restTemplate().exchange(webhookUrl, HttpMethod.POST, httpEntity, String.class);
        log.info("webhook : {}", response.getBody());
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