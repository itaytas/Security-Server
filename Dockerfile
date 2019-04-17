FROM maven:3.6-jdk-8-alpine as maven

# copy pom xml
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy src files
COPY ./src ./src

# build for release
RUN mvn install -DskipTests

#########################################

# our final base image
FROM openjdk:8u181-jdk-alpine

# required for starting application up.
RUN apk update && apk add bash

# create project root folder
RUN mkdir -p /security

# copy over the built artifact from the maven image
COPY --from=maven target/securityServer-0.0.1-SNAPSHOT.jar /security

# copy entire project
COPY . /security

# set working directory
WORKDIR /security

# start the server
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb:27017/security","-Djava.security.egd=file:/dev/./urandom","-jar","./securityServer-0.0.1-SNAPSHOT.jar"]