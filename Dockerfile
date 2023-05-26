#
# Build stage
#
FROM maven:3.8.2-jdk-17 AS build
COPY . .
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-jdk-alpine
COPY --from=build target/ListadeTareas-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]
EXPOSE 8080