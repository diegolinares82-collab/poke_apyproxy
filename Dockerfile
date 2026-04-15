# --- ETAPA 1: Construcción ---
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copiar el archivo POM y descargar dependencias (para aprovechar el caché de capas de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y construir el paquete
COPY src ./src
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Configurar el usuario para mayor seguridad
RUN useradd -m myuser
USER myuser

# Exponer el puerto (Render lo sobreescribirá, pero es buena práctica)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
