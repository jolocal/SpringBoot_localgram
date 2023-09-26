package com.local.localgram.service;

import com.local.localgram.domain.likes.LikesRepository;
import com.local.localgram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;

    // 좋아요
    @Transactional
    public void 좋아요(int imageId, int principalId) {
        try{
            likesRepository.mLikes(imageId, principalId);
        }catch (Exception e){
            throw new CustomApiException("좋아요 실패");
        }
    }

    // 좋아요 취소
    @Transactional
    public void 좋아요취소(int imageId, int principalId){
        likesRepository.mUnlikes(imageId, principalId);
    }
}
