FROM openjdk:11-jre-slim
MAINTAINER Geoffrey Rekier <geoffrey@osnode.com>

VOLUME /tmp

ARG JAR_FILE
#ARG APPLICATION_YML

ADD ${JAR_FILE} /app.jar
#ADD ${APPLICATIONM_YML} /application.yml

EXPOSE 8888

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]