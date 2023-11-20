#
# Build stage
#
FROM maven:3.8.8-opnejdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/iot_backend-0.0.1-SNAPSHOT.jar iot_backend.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]