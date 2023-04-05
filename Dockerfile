FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR socketio-server-demo
COPY --from=build target/*.jar socketio-server-demo.jar
ENTRYPOINT ["java", "-jar", "socketio-server-demo.jar"]