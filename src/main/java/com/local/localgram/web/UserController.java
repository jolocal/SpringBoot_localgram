package com.local.localgram.web;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.user.User;
import com.local.localgram.service.UserService;
import com.local.localgram.web.dto.user.UserProfileDto;
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

    // 프로필 메인
    @GetMapping("/user/{pageOwnerId}")
    public String profile(@PathVariable int pageOwnerId,
                          @AuthenticationPrincipal PrincipalDetails principalDetails,
                          Model model){
        UserProfileDto dto = userService.회원프로필(pageOwnerId,principalDetails.getUser().getId());
        model.addAttribute("dto",dto);
        return "user/profile";
    }

    // 프로필 수정
    @GetMapping("/user/{id}/update")
    public String update(
            @PathVariable int id,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        // 1. 추천
        // log.info("세션정보 : {}",principalDetails.getUser()); -> stackoverflow 발생

        // 2. 비추천
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        // log.info("직접 찾은 세션정보 : {}", mPrincipalDetails.getUser()); -> stackoverflow 발생

        return "user/update";
    }
}
