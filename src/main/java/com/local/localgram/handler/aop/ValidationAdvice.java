package com.local.localgram.handler.aop;

import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Component // IoC 컨테이너에 등록해주는 어노테이션들의 최상위 객체
@Aspect // Aop 처리를 할 수 있게 만들어 주는 어노테이션
@Slf4j
public class ValidationAdvice {

    /*
    @Around : 어떤 특정 함수가 실행되기 전,후 모두 관여
    @Before : 어떤 특정 함수가 실행되기 직전 실행
    @Afer : 어떤 특정 함수가 실행된 후 실행
    */
    @Around("execution(* com.local.localgram.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("----- WEB API CONTROLLER -----");
        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args){
            if (arg instanceof BindingResult){
                log.info("ValidationApiAdvice 유효성 검사 시작");

                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()){
                    HashMap<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(),error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.local.localgram.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("----- WEB CONTROLLER -----");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args){
            if (arg instanceof BindingResult) {
                log.info("ValidationAdvice 유효성 검사 시작");

                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()){
                    HashMap<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(),error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패함",errorMap);
                }
            }

            }

        return proceedingJoinPoint.proceed();
    }
}
