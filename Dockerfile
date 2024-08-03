# Use an official JDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project descriptor files to the container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port your application runs on
EXPOSE 8080

# Copy the built JAR file to the container
COPY target/konnect-0.0.1-SNAPSHOT.jar konnect.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/konnect.jar"]