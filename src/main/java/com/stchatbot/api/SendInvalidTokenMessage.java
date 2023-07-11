package com.stchatbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stchatbot.api.welcomeDtos.WelcomeMessage;
import com.stchatbot.config.RestTemplateConfig;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendInvalidTokenMessage{
    private final RestTemplateConfig restTemplateConfig;
    private final SendWelcomeMessage sendWelcomeMessage;
    private String token = "5134f8ed95a7e1bf-4da86b739fba2b32-bd8aed7bb9812ee4";
    static final String VIBER_SEND_MESSAGE_URL = "https://chatapi.viber.com/pa/send_message";

    public ResponseEntity<?> execute(String accountId) {
        log.info("send invalid message");
        InvalidMessage invalidMessage = new InvalidMessage(accountId, "text", "stchatbot3",
                "Validity time has expired \n" + "if you want to go back \n" + "please click retry button",
                new Keyboard("keyboard",false,"#FFFFFF",
                        Collections.singletonList(new KeyboardButton(
                                6, 1, "#29A709", "reply", "retry",
                                "retry", "center", "middle", "large"))));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Viber-Auth-Token", token);

        HttpEntity<InvalidMessage> invalidMessageHttpEntity = new HttpEntity<>(invalidMessage, httpHeaders);
        return restTemplateConfig.restTemplate().exchange(VIBER_SEND_MESSAGE_URL, HttpMethod.POST, invalidMessageHttpEntity, String.class);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class InvalidMessage {

        private String receiver;
        private String type;
        private String name;
        private String text;
        @JsonProperty("keyboard")
        private Keyboard keyboard;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Keyboard {
        private String Type;
        private boolean DefaultHeight;
        private String BgColor;
        private List<KeyboardButton> Buttons;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class KeyboardButton {
        private int Columns;
        private int rows;
        private String BgColor;
        private String ActionType;
        private String ActionBody;
        private String Text;
        private String TextHAlign;
        private String TextVAlign;
        private String TextSize;
    }
}
