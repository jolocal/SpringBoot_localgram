package com.local.localgram.service;

import com.local.localgram.domain.comment.Comment;
import com.local.localgram.domain.comment.CommentRepository;
import com.local.localgram.domain.image.Image;
import com.local.localgram.domain.user.User;
import com.local.localgram.domain.user.UserRepository;
import com.local.localgram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    // 댓글쓰기
    @Transactional
    public Comment 댓글쓰기(String content, int imageId, int userId){
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(()->{
            throw new CustomApiException("유저 id를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(userEntity);
        comment.setImage(image);

        Comment commentEntity = commentRepository.save(comment);

        return commentEntity;
    }

    //댓글삭제
    @Transactional
    public void 댓글삭제(){
    }
}
