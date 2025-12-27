FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and configs
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

# 🔥 FIX: copy jar to fixed name
RUN cp target/*.jar app.jar

EXPOSE 8080

# 🔥 FIX: run fixed jar
CMD ["java", "-jar", "app.jar"]
