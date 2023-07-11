package com.stchatbot.api;


import com.stchatbot.api.welcomeDtos.WelcomeButton;
import com.stchatbot.api.welcomeDtos.WelcomeKeyboard;
import com.stchatbot.api.welcomeDtos.WelcomeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * welcome message 전송 기능을 위한 클래스 입니다.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class SendWelcomeMessage {

    //@Value("${viber.auth.token}")
    private String token = "5134f8ed95a7e1bf-4da86b739fba2b32-bd8aed7bb9812ee4";

    private String avatar = "https://st-hwadam.s3.ap-northeast-2.amazonaws.com/brand/58c769d2-bf7a-45cd-b60f-09b607f7f600.png";

    private String welcome_media = "";

    public ResponseEntity<WelcomeMessage> execute() {
        log.info("welcome message");
        return ResponseEntity.ok(
                WelcomeMessage.of("Viber Treats", avatar,"tracking_data", "picture",
                        "\uD83E\uDD73\uD83C\uDF89 Hello. Welcome to Viber Treats! We have various treats to send to your friends.\n" +
                                "Click " + "\"Show Treats\"" + " to start",
                        WelcomeKeyboard.of("keyboard", false,"#FFFFFF", createButtons()),
                        welcome_media, welcome_media));
    }

    public List<WelcomeButton> createButtons() {
        return Collections.singletonList(WelcomeButton.of());
    }

}
