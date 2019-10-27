FROM openjdk:8-jre-alpine
MAINTAINER Hoemi
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8484
ENTRYPOINT ["java","-jar","/app.jar"]