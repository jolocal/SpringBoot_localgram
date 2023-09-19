package com.local.localgram.handler;

import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.utill.Script;
import com.local.localgram.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class) // runtimeException이 발생할때 함수 실행
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }
}
