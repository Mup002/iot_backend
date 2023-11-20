#
FROM openjdk:17-alpine AS build
RUN apt-get update && apt-get install -y maven
ENV M2_HOME = C:\Users\84981\Downloads\apache-maven-3.8.8-bin\apache-maven-3.8.8\bin
COPY . .
RUN mvn clean package -Pprod -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/iot_backend-0.0.1-SNAPSHOT.jar iot_backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

