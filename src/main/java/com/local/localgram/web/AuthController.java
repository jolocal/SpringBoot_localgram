package com.local.localgram.web;

import com.local.localgram.domain.user.User;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.service.AuthService;
import com.local.localgram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
@Slf4j
@RequiredArgsConstructor // final 필드를 DI 할때 사용
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }
    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    // 회원가입버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(
            @Valid SignupDto signupDto, //key=value (x-www-form-urlencoded)
            BindingResult bindingResult
    ){
            // User <- SignupDto
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            return "auth/signin";

    }
}
