# FROM openjdk:11
# COPY . /app
#

# Use an appropriate base image for your Spring Boot application
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your Spring Boot application into the container
COPY target/survey-system-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]


