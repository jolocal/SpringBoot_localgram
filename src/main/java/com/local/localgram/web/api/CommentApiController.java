package com.local.localgram.web.api;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.comment.Comment;
import com.local.localgram.handler.ex.CustomValidationApiException;
import com.local.localgram.service.CommentService;
import com.local.localgram.web.dto.CMRespDto;
import com.local.localgram.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 댓글쓰기
    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(
            @Valid @RequestBody CommentDto commentDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        Comment comment = commentService.댓글쓰기(commentDto.getContent(),
                                                commentDto.getImageId(),
                                                principalDetails.getUser().getId()); // content,imageId,userId
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기성공",comment), HttpStatus.CREATED);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> commentDelete(@PathVariable int commentId){
        commentService.댓글삭제(commentId);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제성공",null),HttpStatus.OK);
    }
}
