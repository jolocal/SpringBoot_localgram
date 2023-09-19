package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.user.User;
import com.local.localgram.service.UserService;
import com.local.localgram.web.dto.CMRespDto;
import com.local.localgram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;
    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            UserUpdateDto userUpdateDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        log.info("userApiConroller update start");
        // DB정보 수정
        User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
        // 세션정보 수정
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1,"회원수정완료",userEntity);
    }
}
