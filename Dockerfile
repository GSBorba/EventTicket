# Etapa de build
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Faz o build sem os testes
RUN mvn clean install -DskipTests

# Etapa final com uma imagem leve
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Expondo a porta
EXPOSE 8080

# Copia o JAR gerado
COPY --from=build /app/target/ticket-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada
CMD ["java", "-jar", "app.jar"]
