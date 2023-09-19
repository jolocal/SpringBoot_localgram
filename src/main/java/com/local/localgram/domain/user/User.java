package com.local.localgram.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // DB에 테이블 생성
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @Column(unique = true, length = 20)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String website;
    private String bio; // 자기소개
    @Column(nullable = true)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 프로필이미지
    private String role;
    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
