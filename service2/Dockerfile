FROM openjdk:11-jre-slim
MAINTAINER Geoffrey Rekier <geoffrey@osnode.com>

VOLUME /tmp

ENV ADMIN_SERVICE_URL gcloud-admin:8000
ENV CONFIG_SERVICE_URL gcloud-config:8888
ENV EUREKA_SERVICE_URL gcloud-eureka:8761
ENV OAUTH2_SERVICE_URL gcloud-oauth2:8899
ENV ZIPKIN_SERVICE_URL zipkin:9411
ENV DOCKER_HOSTNAME gcloud-service2

ARG JAR_FILE
#ARG APPLICATION_YML

ADD ${JAR_FILE} /app.jar
#ADD ${APPLICATIONM_YML} /application.yml

EXPOSE 8002

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]