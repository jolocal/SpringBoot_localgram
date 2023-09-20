package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.user.User;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.service.UserService;
import com.local.localgram.web.dto.CMRespDto;
import com.local.localgram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, // *위치가 중요* 꼭 @Valid가 적혀있는 다음 파라미터에 작성
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
                log.info(error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        } else {
            // DB정보 수정
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            // 세션정보 수정
            principalDetails.setUser(userEntity);
           return new CMRespDto<>(1, "회원수정완료", userEntity);
        }
    }
}


