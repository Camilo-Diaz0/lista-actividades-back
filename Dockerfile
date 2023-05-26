#
# Build stage
#
FROM maven:3.8.1-jdk-19 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build target/ListadeTareas-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]
EXPOSE 8080