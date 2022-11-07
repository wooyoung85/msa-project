FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY apiEncrytionKey.jks apiEncrytionKey.jks
COPY target/config-1.0.jar ConfigService.jar
ENTRYPOINT ["java", "-jar", "CofigService.jar"]