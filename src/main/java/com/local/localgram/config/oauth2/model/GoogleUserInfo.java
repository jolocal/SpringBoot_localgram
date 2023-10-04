package com.local.localgram.config.oauth2.model;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String,Object> attributes;

    @Override
    public String getProvider() {
        return "google_";
    }

    @Override
    public String getUsername() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
