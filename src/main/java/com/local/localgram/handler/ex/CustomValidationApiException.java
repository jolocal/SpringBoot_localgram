package com.local.localgram.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationApiException extends RuntimeException {

    // 객체를 구분하기 위해 시리얼넘버를 넣어주는것.
    // JVM을 위해 걸어주는 것이다.
    private static final long serialVersionUID = 1L;

    private Map<String, String> errorMap;

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

}
