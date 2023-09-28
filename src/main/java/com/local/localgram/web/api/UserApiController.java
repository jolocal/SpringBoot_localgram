package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.user.User;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.service.SubscribeService;
import com.local.localgram.service.UserService;
import com.local.localgram.web.dto.CMRespDto;
import com.local.localgram.web.dto.subscribe.SubscribeDto;
import com.local.localgram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    // 프로필 페이지 이미지 변경
    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(
            @PathVariable int principalId,
            MultipartFile profileImageFile,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        log.info("controller start");
        User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
        // 세션변경
        principalDetails.setUser(userEntity);
        return new ResponseEntity<>(new CMRespDto<>(1,"회원 프로필 사진 변경 성공",null),HttpStatus.OK);
    }

    // 현재 페이지 주인의 구독자 정보 리스트
    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(
            @PathVariable int pageUserId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        List<SubscribeDto> subscribeDtos = subscribeService.구독리스트(principalDetails.getUser().getId(),pageUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 불러오기 성공",subscribeDtos), HttpStatus.OK);
    }

    // 회원수정
    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, // *위치가 중요* 꼭 @Valid가 적혀있는 다음 파라미터에 작성
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
            // DB정보 수정
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            // 세션정보 수정
            principalDetails.setUser(userEntity);
           return new CMRespDto<>(1, "회원수정완료", userEntity); // 응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.

    }
}


