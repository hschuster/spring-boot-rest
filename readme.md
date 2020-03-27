# spring-boot-rest

## Aufruf (Externe IP : Interne IP) 
docker run -p 8585:8484 spring-boot-rest

curl http://localhost:8585/test?test=Hoemi
curl --header "X-Mandant:mandant02" http://localhost/test?test=hoemi


curl -d "client_id=login-app" -d "username=user1" -d "password=pwd1" -d "grant_type=password" "http://sbspielwiese:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token"
curl localhost:8484/test?test=Hoemi -H 'Authorization: Bearer <Token>'

