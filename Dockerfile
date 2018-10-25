FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/simple-game-0.0.1-SNAPSHOT.jar /usr/src/simple-game-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/simple-game-0.0.1-SNAPSHOT.jar"]
