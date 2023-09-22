package com.local.localgram.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException {

    // 객체를 구분할 때
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

}
