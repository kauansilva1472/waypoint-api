# Etapa 1: compila o projeto com o Maven Wrapper (Java 25)
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw -B -q package -DskipTests

# Etapa 2: imagem final, só com o Java e o .jar gerado
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Limite de memória para caber no plano gratuito do Render (512 MB)
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]
