# Use an official Maven/Java runtime as a parent image
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory to your application's directory
WORKDIR /usr/src/app

# Copy the POM file and the rest of your application
COPY pom.xml .
COPY src src

# Build the application
RUN mvn clean package

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/my-app.jar"]