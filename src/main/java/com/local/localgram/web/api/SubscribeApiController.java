package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.service.SubscribeService;
import com.local.localgram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    // 구독하기
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable int toUserId
    ){
        subscribeService.구독하기(principalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null), HttpStatus.OK);
    }

    // 구독취소하기
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable int toUserId
    ){
        subscribeService.구독취소하기(principalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 실패",null), HttpStatus.OK);
    }
}

