# Base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Set the container name
ENV CONTAINER_NAME logging-service

# Expose the application port
EXPOSE 9000

# Set the entry point
ENTRYPOINT ["bash", "/app/start.sh"]