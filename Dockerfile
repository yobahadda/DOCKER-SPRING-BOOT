FROM openjdk:17-slim

WORKDIR /app

COPY target/Test.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar"]