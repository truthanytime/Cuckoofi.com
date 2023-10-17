FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY ./auth-service/target/auth-service.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]