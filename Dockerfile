FROM maven:3.8.3-openjdk-17 as build-stage
LABEL version="1.0"
ADD . /src
WORKDIR /src
RUN mvn clean compile package -Dmaven.test.skip=true
EXPOSE 8082
#ENTRYPOINT ["java","-jar","target/cashregister.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "target/cashregister.jar"]