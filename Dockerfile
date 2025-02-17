# Etapa de build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/ticket-0.0.1-SNAPSHOT.jar app.jar

# Garante que o Spring Boot escute na porta correta definida pelo Cloud Run
CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]
