package com.local.localgram.config.auth;

import com.local.localgram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private static final long serialVersionUID = 1L;

    private User user;
    private Map<String,Object> attribute;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // OAuth2 유저 구분을 위한 오버로딩
    public PrincipalDetails(User user,Map<String,Object> attribute){
        this.user = user;
    }


    // 권한 : 한개가 아닐 수 있음. (3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {
            return user.getRole();
        });
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // OAuth2User 타입을 implement
    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }
    @Override
    public String getName() {
        return (String) attribute.get("name");
    }
}
