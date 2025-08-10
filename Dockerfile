# Step 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the rest of the project and build
COPY . ./
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Install PostgreSQL client (optional, for debugging inside container)
RUN apt-get update && apt-get install -y postgresql-client && rm -rf /var/lib/apt/lists/*

# Copy the built JAR from the build stage
COPY --from=build /app/target/practice-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Use environment variables (loaded at runtime via .env, no secrets here)
ENTRYPOINT ["java", "-jar", "app.jar"]
