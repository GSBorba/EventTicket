FROM ubuntu:latest AS build

# Instala o JDK e Maven
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven && \
    apt-get clean

# Copia o código-fonte para o contêiner
COPY . /app
WORKDIR /app

# Executa o build, pulando os testes
RUN mvn clean install -DskipTests

# Verifica o conteúdo do diretório target
RUN ls -la target

# Cria a imagem final
FROM openjdk:21-jdk-slim
WORKDIR /app

# Expondo a porta da aplicação
EXPOSE 8080

# Copia o JAR gerado para a nova imagem
COPY --from=build /app/target/ticket-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
