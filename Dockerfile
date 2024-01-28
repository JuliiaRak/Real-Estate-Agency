FROM maven:3.9.6-eclipse-temurin-11-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml -DskipTests clean install

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/Real-Estate-Agency-1.0-SNAPSHOT.jar /usr/app/Real-Estate-Agency-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/Real-Estate-Agency-1.0-SNAPSHOT.jar"]