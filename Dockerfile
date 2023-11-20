#
#FROM openjdk:17-alpine AS build
#RUN apt-get update && apt-get install -y maven
#COPY . .
#RUN mvn clean package -DskipTests
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/iot_backend-0.0.1-SNAPSHOT.jar iot_backend.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","demo.jar"]

FROM openjdk:17-alpine
VOLUME /tmp
COPY target/iot_backend-0.0.1-SNAPSHOT.jar iot_backend.jar
ENTRYPOINT ["java","-jar","/iot_backend.jar"]
EXPOSE 8080
