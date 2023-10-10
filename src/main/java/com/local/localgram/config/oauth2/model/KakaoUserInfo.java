package com.local.localgram.config.oauth2.model;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String,Object> attributes;
    @Override
    public String getProvider() {
        return "kakao_";
    }

    @Override
    public String getUsername() {
        return attributes.get("id").toString();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getEmail() {
        // kakao_account Map에서 추출
        return (String) ((Map) attributes.get("kakao_account")).get("email");
    }

    @Override
    public String getName() {
        // kakao_account Map에서 추출
        return (String) ((Map) attributes.get("properties")).get("nickname");

    }
}
