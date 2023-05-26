FROM openjdk:19-jdk-alpine
COPY target/ListadeTareas-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]
EXPOSE 8080