package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.image.Image;
import com.local.localgram.domain.likes.Likes;
import com.local.localgram.service.ImageService;
import com.local.localgram.service.LikesService;
import com.local.localgram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    // 이미지 스토리 보기
    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PageableDefault(size = 3) Pageable pageable
    ){
        Page<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(
                new CMRespDto<>(1,"이미지스토리 불러오기 성공",images), HttpStatus.OK);
    }

    // 좋아요
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(
            @PathVariable int imageId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        likesService.좋아요(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(
//                new CMRespDto<>(1,"좋아요 성공",null),HttpStatus.OK);
                new CMRespDto<>(1,"좋아요 성공",null),HttpStatus.CREATED);
    }

    // 좋아요 취소
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(
            @PathVariable int imageId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        likesService.좋아요취소(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(
                new CMRespDto<>(1,"좋아요 취소 성공",null),HttpStatus.OK);
    }

}
