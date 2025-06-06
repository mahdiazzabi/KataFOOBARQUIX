FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/target/KataFOOBARQUIX-0.0.1-SNAPSHOT.jar app.jar

# Ajoute les fichiers batch d'entrée/sortie
COPY input/input.txt /app/input/input.txt

# Expose les volumes pour lecture/écriture
VOLUME ["/app/input", "/app/output"]

ENTRYPOINT ["java", "-jar", "app.jar"]
