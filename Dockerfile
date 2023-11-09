FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/T1-Consulting-test-task-0.0.1.jar app.jar

CMD ["java", "-jar", "app.jar"]