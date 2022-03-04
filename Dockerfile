FROM maven:3.8.4-openjdk-17 AS maven
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest
ENV PORT 8080

FROM openjdk:17 as runtime

WORKDIR /app
COPY --from=maven ./target/Movie-Characters-API-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "Movie-Characters-API-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080