package com.local.localgram.handler;

import com.local.localgram.handler.ex.CustomApiException;
import com.local.localgram.handler.ex.CustomException;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.utill.Script;
import com.local.localgram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    // JavaScript 응답하는 핸들러
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        /*
        CMRespDto, Script 비교
        1. 클라이언트에게 응답할때는 Script 좋음
        2. Ajax 통신을 하거나 안드로이드와 통신을 하게 되면 CMRespDto가 좋음
        3. 즉, 개발자를 위한 응답에는 CMRespDto, 클라이언트를 위해서는 Script가 좋음.
         */
        if (e.getErrorMap() == null){
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    // profile 페이지에서 사용될 핸들러
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }


    // CMRespDto 오브젝트를 응답하는 핸들러
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}
