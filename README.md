## 목차
- [모듈 구조 설명](./README_MODULE.md)
- [BATCH 모듈 설명](./README_BATCH.md)
- [CORE 모듈 설명](./README_CORE.md)
- [MEMBER 모듈 설명](./README_MEMBER.md)

[프로젝트 설명(demo-module-back)]
======================

## 플로우
- controller -> domain(service) -> module-domain(DB)
- controller : 전체 구조를 볼 수 있게 한 곳에 정의
- domain : 도메인 따른 로직 처리

## jpa pk 전략 Tsid
- jpa pk 전략 관련해서 확인 결과 tsid 사용 : test-0HGQMGCPXYEJK
- PfMemberToken 도메인 처럼 로그 관련 pk는 auto_increment 처리
  - 참조 : https://docs.tosspayments.com/resources/glossary/uuid
  - 참조 : https://ssdragon.tistory.com/162
  

## 설정
- spring.pid.file: pid파일 처리
- spring.data.we.pageable: 페이징 공통 처리

## 로그
- logback-spring.xml를 통한 공통 로그 처리

## 오류 공통 처리
- @RestControllerAdvice를 통한 오류 공통 처리
- @ExceptionHandler({InsufficientAuthenticationException.class, BadCredentialsException.class, CustomAuthenticationException.class, AuthenticationException.class}) : 스프링 시큐리티 검증 오류 처리 
