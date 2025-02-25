# Dockerfile para Microservicio 1 y 2 (similar estructura)
FROM openjdk:17-jdk-slim

# Añadir metadatos
LABEL maintainer="jordyjaja@hotmail.com"
LABEL version="1.0"

WORKDIR /app
COPY target/microservicio1.jar app.jar

# Exponer puerto
EXPOSE 8081

# Establecer punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]