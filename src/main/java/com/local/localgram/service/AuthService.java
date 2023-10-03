package com.local.localgram.service;

import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import com.local.localgram.handler.ex.CustomException;
import com.local.localgram.handler.ex.CustomValidationException;
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
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);

        // 권한 설정
        user.setRole("ROLE_USER"); //관리자 ROLE_ADMIN

        User userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity == null){
            return userRepository.save(user);
        } else {
            throw new CustomValidationException("이미 존재하는 아이디입니다.");
        }
    }
}
