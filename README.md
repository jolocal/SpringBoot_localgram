## :computer: 프로젝트 소개
인스타그램 클론 코딩은 `Spring Boot`와 `JPA`를 사용하여 이미지 기반 소셜 미디어 인스타그램 서비스를 구현한 프로젝트입니다.

## :pencil2: NOTION
[NOTION 바로가기](https://www.notion.so/83bfdf81e7dd411881f3ab37a3e8a27f?v=1e7b082fe56f40d1af3ab8846e08ccdc&pvs=4)


## :low_brightness: 개발 환경
- **Language** : `Java 11`
- **Framework** : `Spring Boot (2.x)`
- **Authorization** : `Spring Security`
- **ORM** : `Spring Data JPA`, `Querydsl`
- **Database** : `Maria DB`
- **IDE** : `IntelliJ`
- **API Test** : `Postman`


## :mag_right: 사용 기술

#### JPA
- 연관관계 ManyToOne, OneToMany, ManyToMany
- 무한참조 방지
- 양방향 매핑
- 복잡한 모델 DTO 매핑방법 - QLRM 라이브러리
- SQL - 스칼라 서브 쿼리, 인라인 뷰, 조인 활용
  
#### 스프링부트 open-in-view와 Lazy loading
#### ResponseEntity와 공통 응답을 위한 DTO
#### 커스텀 예외 핸들링 - [상세보기)(https://github.com/jolocal/SpringBoot_localgram/wiki/%EC%BB%A4%EC%8A%A4%ED%85%80-%EC%98%88%EC%99%B8-%ED%95%B8%EB%93%A4%EB%A7%81)
#### AOP를 이용하여 유효성 검사 자동화 - [상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/AOP%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%82%AC-%EC%9E%90%EB%8F%99%ED%99%94#aop%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%82%AC-%EC%9E%90%EB%8F%99%ED%99%94)
#### 스프링 시큐리티
#### OAuth2
#### 기본 로그인과 페이스북 로그인 세션 통합 관리


## :pushpin: 주요 기능

#### 로그인
- DB값 검증
- ID 찾기, PW 찾기
- 로그인 시 쿠키(Cookie) 및 세션(Session) 생성
- OAuth2 로그인
- 로그아웃
  

#### 회원가입
- 주소 API 연동
- ID 중복 체크
- 프론트,백엔드 유효성 검사
  

#### 스토리 페이지
- 게시물 무한 스크롤 조회
- 게시물 좋아요,좋아요 취소
- 댓글 등록,수정,삭제


#### 인기 페이지
- 좋아요가 많은 순으로 뷰 랜더링


#### 팔로우
- 팔로우
- 언팔로우


#### 프로필 페이지
- 구독,구독 취소하기
- 프로필 이미지 등록
- 회원 프로필 수정
- 게시물 업로드

