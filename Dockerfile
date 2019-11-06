FROM openjdk:8-jre-alpine
MAINTAINER Hoemi
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-Dspring.config.location=file:applicationDocker.properties"]