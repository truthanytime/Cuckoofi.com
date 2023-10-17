FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY ./gateway-service/target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]