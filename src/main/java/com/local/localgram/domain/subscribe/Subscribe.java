package com.local.localgram.domain.subscribe;

import com.local.localgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk", // uniquekey name
                        columnNames = { // Unique 제약조건을 적용할 컬럼명
                                "fromUserId",
                                "toUserId"
                        }
                )
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fromUserId") // DB 컬럼명 지정
    private User fromUser; // 구독하는 유저

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser; // 구독받는 유저

    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}

