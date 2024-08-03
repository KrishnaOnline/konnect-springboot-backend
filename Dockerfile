FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/konnect-0.0.1-SNAPSHOT.jar konnect.jar
ENTRYPOINT ["java","-jar","/konnect.jar"]