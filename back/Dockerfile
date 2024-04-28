FROM openjdk:17-jdk-slim
COPY . .
COPY target/back.jar back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "back.jar"]