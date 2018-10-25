FROM openjdk:11-jre-slim
MAINTAINER Geoffrey Rekier <geoffrey@osnode.com>

VOLUME /tmp

ENV EUREKA_SERVICE_URL gcloud-eureka:8761
ENV CONFIG_SERVICE_URL gcloud-config:8888
ENV DOCKER_HOSTNAME gcloud-oauth2

ARG JAR_FILE
#ARG APPLICATION_YML

ADD ${JAR_FILE} /app.jar
#ADD ${APPLICATIONM_YML} /application.yml

EXPOSE 8899

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]