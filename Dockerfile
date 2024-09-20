# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/route-service-0.0.1-SNAPSHOT.jar /app/route-service.jar

# Expose the port for the route-service
EXPOSE 9124

# Run the application
ENTRYPOINT ["java", "-jar", "/app/route-service.jar"]

