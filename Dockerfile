FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ListadeTareas-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]
EXPOSE 8080