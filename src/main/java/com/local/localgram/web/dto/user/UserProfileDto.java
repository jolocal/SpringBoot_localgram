package com.local.localgram.web.dto.user;

import com.local.localgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    private boolean pageOwnerStatus; // 페이지 주인 여부
    private int imageCount; // 업로드된 이미지 갯수
    private User user; // 접속한 유저 정보를 받을 유저 오브젝트
    private boolean subscribeStatus; // 구독상태 (구독o:true, 구독x:false)
    private int subscribeCount; // 구독자수
}

