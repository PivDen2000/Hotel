FROM maven:3.8.3-openjdk-16-slim AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean install
FROM openjdk:16-jdk
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app/backend.jar"]
