package com.local.localgram.service;

import com.local.localgram.domain.subscribe.SubscribeRepository;
import com.local.localgram.handler.ex.CustomApiException;
import com.local.localgram.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager entityManager; // Repository는 EntityManager를 구현해서 만들어져 있는 구현체

    // 구독자 정보 리스트
    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {

        // 쿼리 준비
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeStatus, ");
        sb.append("if((? = u.id), 1, 0 ) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?"); // 세미콜론 첨부하면 안됨.

        // 1.물음표: principalId
        // 2.물음표: principalId
        // 3.물음표: pageUserId

        // 쿼리 완성
        Query query = entityManager.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 db결과를 매핑하기 위해서)
        JpaResultMapper resultMapper = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = resultMapper.list(query, SubscribeDto.class);

        return subscribeDtos;
    }

    @Transactional
    public void 구독하기(int fromUserId, int toUserId){
        log.info("구독하기 서비스 시작");
        try{
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e){
            throw new CustomApiException("이미 구독한 유저입니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

}
