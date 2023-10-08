## :computer: 프로젝트 소개
`Spring Boot`와 `JPA`를 사용하여 이미지 기반 소셜 미디어 인스타그램 서비스를 구현한 프로젝트입니다.

## :pencil2: NOTION
프로젝트 진행과정, 오류 해결 방법, 궁금한 점 등을 자세히 기록한 노션 페이지입니다.

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

#### 커스텀 예외 핸들링 - [상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%82%AC%EC%9A%A9%EA%B8%B0%EC%88%A0%5D-%EC%BB%A4%EC%8A%A4%ED%85%80-%EC%98%88%EC%99%B8-%ED%95%B8%EB%93%A4%EB%A7%81)
#### AOP를 이용하여 유효성 검사 자동화 - [상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%82%AC%EC%9A%A9%EA%B8%B0%EC%88%A0%5D-AOP%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%82%AC-%EC%9E%90%EB%8F%99%ED%99%94)
#### 스프링 시큐리티 - [상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%82%AC%EC%9A%A9%EA%B8%B0%EC%88%A0%5D-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0)
#### OAuth2 - [상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%82%AC%EC%9A%A9%EA%B8%B0%EC%88%A0%5D-OAuth2)


## :pushpin: 주요 기능

#### 로그인 - [코드 상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%5D-%EB%A1%9C%EA%B7%B8%EC%9D%B8)


<details>
<summary>시연 영상 보기</summary>
  
<p align="center">
  <img src="https://github.com/jolocal/SpringBoot_localgram/assets/129312146/bc605a94-e2d6-418a-90f9-34112f7ea17e">
</p>

</details>

  
- 스프링 시큐리티
- DB값 검증
- 로그인시 일반 로그인과 OAuth2 로그인 통합세션 관리
- OAuth2 로그인


#### 회원가입 - [코드 상세보기](https://github.com/jolocal/SpringBoot_localgram/wiki/%5B%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%5D-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85#computer-%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81)


<details>
<summary>시연 영상 보기</summary>
  
<p align="center">
  <img src="https://github.com/jolocal/SpringBoot_localgram/assets/129312146/0f2863b8-ae45-4400-b4eb-9ee27fa827f5">
</p>

</details>


- 회원가입시 중복 체크
- 프론트,백엔드 유효성 검사
- 글로벌 예외 처리하기


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


