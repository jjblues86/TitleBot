FROM eclipse-temurin:17

LABEL maintainer="jeromejoof@yahoo.com"

WORKDIR /app

COPY target/springboot-titlebot-rest-api-0.0.1-SNAPSHOT.jar springboot-titlebot-project.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springboot-titlebot-project.jar"]
