FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} config-client.jar
ENTRYPOINT ["java", "-jar", "/config-client.jar"]