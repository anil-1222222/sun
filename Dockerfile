FROM openjdk:11
MAINTAINER "Ashok Bollepalli <797979>"
COPY target/Proj1.jar  /usr/app/
WORKDIR /usr/app/
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "Proj1.jar"]
