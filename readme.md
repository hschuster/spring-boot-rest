# spring-boot-rest

## Aufruf (Externe IP : Interne IP) 
docker run -p 8585:8484 spring-boot-rest

curl http://localhost:8585/test?test=Hoemi
curl --header "X-Mandant:mandant02" http://localhost/test?test=hoemi

