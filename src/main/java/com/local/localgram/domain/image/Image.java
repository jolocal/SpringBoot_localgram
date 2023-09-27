package com.local.localgram.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.local.localgram.domain.likes.Likes;
import com.local.localgram.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption;
    private String postImageUrl; // image가 서버로 전송되어 저장되는 경로

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"images"})
    private User user;

    // 이미지 좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    // 좋아요 상태
    @Transient // DB에 컬럼이 만들어지지 않는다.
    private boolean likeStatus;

    // 좋아요 갯수
    @Transient
    private int likeCount;

    //댓글

    private LocalDateTime createDate;
    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}