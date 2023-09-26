package com.local.localgram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.validation.Valid;

public interface LikesRepository extends JpaRepository<Likes,Integer> {

    // 좋아요
    @Modifying
    @Query(value = "INSERT INTO likes(imageId,userId,createDate) VALUES(:imageId,:principalId,NOW())",nativeQuery = true)
    public void mLikes(int imageId, int principalId);

    // 좋아요 취소
    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId",nativeQuery = true)
    public void mUnlikes(int imageId, int principalId);
}
