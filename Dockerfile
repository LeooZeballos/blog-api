# Use a base image with JDK 17 and Maven
FROM maven:3.8.3-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a new stage using the base JDK 17 image
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which your Spring Boot application listens
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
