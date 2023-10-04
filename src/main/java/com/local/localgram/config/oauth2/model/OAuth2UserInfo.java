package com.local.localgram.config.oauth2.model;
public interface OAuth2UserInfo {
    String getProvider();
    String getUsername();
    String getPassword();
    String getEmail();
    String getName();
}
