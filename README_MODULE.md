## 목차
- [README](./README.md)
- [모듈 구조 설명](./README_MODULE.md)
- [BATCH 모듈 설명](./README_BATCH.md)
- [CORE 모듈 설명](./README_CORE.md)
- [MEMBER 모듈 설명](./README_MEMBER.md)
- [아키텍처 모듈 설명](./README_ARCHITECTURE.md)


[모듈 구조 설명]
====================== 

## demo-app-admin
- 관리자 서버

## demo-app-batch
- 배치 서버
- Spring Batch + quartz 적용

## demo-app-user
- 사용자 서버

## demo-core
- 공통 처리(enum, jpa, entity)

## demo-domain-xx
- 테스트(test), 회원(member) 등등 도메인 처리
- service -> RepositoryImpl : Querydsl 사용
- demo-doamin-member : Spring Security, Jwt 등등

## demo-infra-aws
- aws s3 설정 
- 파일 등록, 수정, 삭제 처리

## demo-infra-mariadb
- AWS RDS : MariaDB 10.11.8

## demo-infra-redis
- 회원 토큰 처리 및 재고 처리를 위한 레디스 모듈 추가 필요 