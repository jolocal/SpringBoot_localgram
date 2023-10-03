package com.local.localgram.service;

import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 1.IoC 2.트랜잭션 관리
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Transactional // Write(Insert,Update, Delete)
    public User 회원가입(User user){
        // 회원가입 진행

        // 비밀번호 암호화
        String rawPassword = user.getPassword();
        log.info("rawPassword: {}", rawPassword);
        String encPassword = encoder.encode(rawPassword);
        log.info("encPassword: {}", rawPassword);
        user.setPassword(encPassword);
        // 권한 설정
        user.setRole("ROLE_USER"); //관리자 ROLE_ADMIN

        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
