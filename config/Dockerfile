FROM openjdk:17-ea-11-jdk-slim
VOLUME /repo
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-1.0.jar ConfigService.jar
ENTRYPOINT ["java", "-jar", "ConfigService.jar"]