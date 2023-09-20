package com.local.localgram.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message);
    }

}
