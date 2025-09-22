# ============================
# 1. Etapa de construcción
# ============================
FROM gradle:8.9-jdk21 AS builder
WORKDIR /home/gradle/project

# Copiar todo el código fuente
COPY . .

# Compilar y generar el JAR
RUN gradle clean build -x test

# ============================
# 2. Etapa de runtime (ligera)
# ============================
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /home/gradle/project/applications/app-service/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]