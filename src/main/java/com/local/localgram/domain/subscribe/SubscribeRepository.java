package com.local.localgram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    // 구독하기 네이티브 쿼리
    @Modifying // INSERT, DELETE, UPDATE 를 네이티브쿼리로 작성하려면 해당 어노테이션이 필요
    @Query(value = "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",
            nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);

    // 구독취소하기 네이티브 쿼리
    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId =: formUserId AND toUserId =: toUserId)",
            nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);
}
