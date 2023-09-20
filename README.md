# OAuth-Test-Backend

- Java : v17

- 백엔드 포드 : 8080

- application-secret.yml 파일 내용
  ```yml
  oauth:
  kakao:
    client_id: # REST API 키
    redirect_uri: http://localhost:3000/oauth/kakao/redirect
    client_secret: # Client Secret 키
    scope: profile_nickname, profile_image
  ```
