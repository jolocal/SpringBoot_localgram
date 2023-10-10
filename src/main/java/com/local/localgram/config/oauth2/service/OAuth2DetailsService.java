package com.local.localgram.config.oauth2.service;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.config.oauth2.model.*;
import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보 불러오기
        Map<String,Object> userInfo = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            log.info("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")){
            log.info("구글 로그인 요청");
            log.info("userInfo:{}",userInfo);
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            log.info("네이버 로그인 요청");
            log.info("userInfo:{}",userInfo);
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }
        if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            log.info("카카오 로그인 요청");
            log.info("userInfo:{}",userInfo);
            oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());
            log.info("getAttributes : {}",oAuth2User.getAttributes());
        }

        String username = oAuth2UserInfo.getProvider() + oAuth2UserInfo.getUsername();
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();


        User userEntity = userRepository.findByUsername(username);

        // 최초 로그인
        if (userEntity == null){

            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER")
                    .build();

            return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());

        } else {
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
        }
    }
}
