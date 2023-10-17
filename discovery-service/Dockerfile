FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY ./discovery-service/target/discovery-service.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]