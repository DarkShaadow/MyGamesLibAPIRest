# JDK 17
FROM openjdk:17-jdk-alpine

# Copier le jar
COPY my-games-lib-api-rest.jar .

# Timezone
ENV TZ=Europe/Paris
ENV ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /var/lib/docker/volumes/files/picture
RUN mkdir -p /var/lib/docker/volumes/files/video

# Ports exposés
EXPOSE 8080/tcp

# Lancer l'application
ENTRYPOINT ["java","-jar","/my-games-lib-api-rest.jar"]