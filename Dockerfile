# Multi-stage build with Maven
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app

# First copy only POM files of known services
COPY service-registry/pom.xml ./service-registry/
COPY cloud-config-server/pom.xml ./cloud-config-server/# Multi-stage build
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app
COPY . .

# Build each service separately with error handling
RUN for service in service-registry cloud-config-server api-gateway auth-service rate-aggregation-service; do \
      if [ -d "$service" ]; then \
        echo "Building $service..."; \
        cd $service && \
        mvn clean package -DskipTests && \
        cd ..; \
      else \
        echo "Warning: Directory $service not found"; \
      fi \
    done

# Final image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy built jars
COPY --from=builder /app/service-registry/target/*.jar ./service-registry.jar
COPY --from=builder /app/cloud-config-server/target/*.jar ./cloud-config-server.jar
COPY --from=builder /app/api-gateway/target/*.jar ./api-gateway.jar
COPY --from=builder /app/auth-service/target/*.jar ./auth-service.jar
COPY --from=builder /app/rate-aggregation-service/target/*.jar ./rate-aggregation-service.jar

# [Rest of your Dockerfile...]
COPY api-gateway/pom.xml ./api-gateway/
COPY auth-service/pom.xml ./auth-service/
COPY rate-aggregation-service/pom.xml ./rrate-aggregation-service/

# Download dependencies (with error suppression)
RUN for dir in service-registry cloud-config-server api-gateway auth-service rate-aggregation-service; do \
      [ -f "$dir/pom.xml" ] && \
      mvn -f "$dir/pom.xml" dependency:go-offline || echo "Skipping $dir (no POM)"; \
    done

# Copy all source code
COPY . .

# Build only specified services
RUN mvn -pl service-registry,cloud-config-server,api-gateway,auth-service,rate-aggregation-service clean package -DskipTests

# Final image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy built jars (with wildcard to handle version numbers)
COPY --from=builder /app/service-registry/target/*.jar ./service-registry.jar
COPY --from=builder /app/cloud-config-server/target/*.jar ./cloud-config-server.jar
COPY --from=builder /app/api-gateway/target/*.jar ./api-gateway.jar
COPY --from=builder /app/auth-service/target/*.jar ./auth-service.jar
COPY --from=builder /app/rate-aggregation-service/target/*.jar ./rate-aggregation-service.jar

# [Rest of your original Dockerfile...]