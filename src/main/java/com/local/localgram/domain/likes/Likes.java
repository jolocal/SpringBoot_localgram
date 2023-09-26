package com.local.localgram.domain.likes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.local.localgram.domain.image.Image;
import com.local.localgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "likes_uk",
        columnNames = {"imageId", "userId"})
})
public class Likes { // N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 무한 참조됨
    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image; // 1

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"images"})
    private User user; // 1

    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}





