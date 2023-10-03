package com.local.localgram.service;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.subscribe.SubscribeRepository;
import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import com.local.localgram.handler.ex.CustomApiException;
import com.local.localgram.handler.ex.CustomException;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final SubscribeRepository subscribeRepository;

    // 5. yml 파일에 지정한 경로 호출
    @Value("${file.path}")
    private String uploadFolder;

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

        // 좋아요 갯수 추가
        userEntity.getImages().forEach((image) ->{
            image.setLikeCount(image.getLikes().size());
        });

        return dto;
    }

    @Transactional
    public User 회원수정(int id, User user) {
        log.info("UserService 회원수정 start");

        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new CustomValidationException("찾을 수 없는 Id입니다.");
        });

        // 2. 영속화된 오브젝트를 수정 -> 더티체킹 -> 업데이트완료
        userEntity.setName(user.getName());
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        if (userEntity.getPassword() == null){
            return userEntity;
        } else {
            userEntity.setPassword(encPassword);
            return userEntity;
        }
        // 더티체킹이 일어나서 업데이트가 완료됨.
    }

    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
        log.info("service start");
        UUID uuid = UUID.randomUUID();

        // uuid + _ + 이미지 파일 이름 설정
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
        // 이미지 파일 경로 설정
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 에외가 발생할 수 있음
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("유저를 찾을 수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;

    } // 더티체킹으로 업데이트 됨.
}
