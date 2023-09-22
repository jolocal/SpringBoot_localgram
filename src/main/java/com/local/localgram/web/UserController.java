package com.local.localgram.web;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.user.User;
import com.local.localgram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model){
        User userEntity = userService.회원프로필(id);
        model.addAttribute("user",userEntity);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(
            @PathVariable int id,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        // 1. 추천
        log.info("세션정보 : {}",principalDetails.getUser());

        // 2. 비추천
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        log.info("직접 찾은 세션정보 : {}", mPrincipalDetails.getUser());

        return "user/update";
    }
}
