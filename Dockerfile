# First stage: Build the application
FROM eclipse-temurin:17-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project descriptor files to the container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Second stage: Create the runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the first stage
COPY --from=build /app/target/konnect-0.0.1-SNAPSHOT.jar konnect.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/konnect.jar"]