#!/bin/bash

echo "Starting..."

#Init Variable values
CONTAINER_NAME=logging-service

echo Container name will be "->" $CONTAINER_NAME

# Restart container
echo Restarting container $CONTAINER_NAME
docker restart $CONTAINER_NAME
echo Container $CONTAINER_NAME running.

echo Completed successfully.

echo Waiting for service to be ready to serve requests...

bash health_check.sh

echo Service ready