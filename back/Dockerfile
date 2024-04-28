FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/back.jar /app
EXPOSE 8080
CMD ["java", "-jar", "back.jar"]