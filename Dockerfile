FROM maven:jdk17 AS maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest

FROM openjdk:17
WORKDIR /app
COPY --from=maven ./target/Movie-Characters-API-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "Movie-Characters-API-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080