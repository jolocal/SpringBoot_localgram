package com.local.localgram.service;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.subscribe.SubscribeRepository;
import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import com.local.localgram.handler.ex.CustomException;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final SubscribeRepository subscribeRepository;
    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(
            int pageUserId,
            int principalId
    ){
        UserProfileDto dto = new UserProfileDto();

        // select * from image where userId =: userId;
        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });
        // 해당 페이지 유저 정보
        dto.setUser(userEntity);
        // 해당 페이지 유저 상태
        dto.setPageOwnerStatus(pageUserId == principalId);
        // 이미지 갯수
        dto.setImageCount(userEntity.getImages().size());

        // 구독정보
        int subscribeStatus = subscribeRepository.mSubscribeStatus(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeStatus(subscribeStatus == 1);
        dto.setSubscribeCount(subscribeCount);

        return dto;
    }

    @Transactional
    public User 회원수정(int id, User user) {
        log.info("UserService 회원수정 start");
        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(()
                -> new CustomValidationException("찾을 수 없는 Id입니다."));

        // 2. 영속화된 오브젝트를 수정 -> 더티체킹 -> 업데이트완료
        userEntity.setName(user.getName());

        // password 인코딩
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        userEntity.setPassword(encPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());


        return userEntity;
        // 더티체킹이 일어나서 업데이트가 완료됨.
    }
}
