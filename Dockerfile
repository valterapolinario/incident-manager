FROM maven:3.8.5-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml mvn clean package

FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]

