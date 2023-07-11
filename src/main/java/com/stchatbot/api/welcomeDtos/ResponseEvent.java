package com.stchatbot.api.welcomeDtos;

import org.springframework.http.ResponseEntity;

public abstract class ResponseEvent {

    public abstract ResponseEntity<?> execute(String callback);
}
