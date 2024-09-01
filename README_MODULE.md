## 목차
- [README](./README.md)

[모듈 설명]
======================

### 공통 처리
    1. ExceptionControllerAdvice를 통해 오류 공통 처리

### - demo-app-admin(관리자 서버 모듈)
    1. gradle : spring security, AWS S3 파일 처리 적용
    2. application.yml : member, aws, db 사용
    3. service : 회원, 로그인 및 token 도메인 처리, test domain, s3 file 처리

### - demo-app-batch(배치 서버 모듈)
    1. spring batch, quartz 적용
    2. application.yml : member만 사용
    3. service : 예제관련 기능 개발은 미적용 상태

### - demo-app-ceo(배민으로 가정하면 점주들 관련 서버)
    1. gradle : spring security, AWS S3 파일 처리 적용
    2. application.yml : member, aws, db 사용
    3. service : tset domain만 적용

### - demo-app-user(배민으로 가정하면 사용자 어플 관련 서버)
    1. gradle : spring security만 적용
    2. application.yml : member, aws, db 사용
    3. service : member domain, 로그인 및 token 도메인 처리

### - demo-app-user(배민으로 가정하면 사용자 어플 관련 서버)
    1. gradle : spring security만 적용
    2. application.yml : member, aws, db 사용
    3. service : member domain, 로그인 및 token 도메인 처리



