#!/bin/sh

# Start services in order with delays
java -jar service-registry.jar &
java -jar cloud-config-server.jar &
./wait-for-it.sh localhost:8761 -t 60 -- java -jar api-gateway.jar &
./wait-for-it.sh localhost:9191 -t 60 -- java -jar auth-service.jar &
./wait-for-it.sh localhost:8081 -t 60 -- java -jar rate-aggregation-service.jar &

# Keep container running
tail -f /dev/null