# Usa una imagen base de OpenJDK con Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Crea un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo POM y los archivos fuente de la aplicación al contenedor
COPY pom.xml .

# Descarga las dependencias del proyecto (esto se hace por separado para aprovechar la cache de Docker)
RUN mvn dependency:go-offline

# Copia el resto de los archivos del proyecto
COPY src ./src

# Compila y empaqueta la aplicación (usando el perfil 'production' si es necesario)
RUN mvn clean package -DskipTests

# Segunda fase: utilizar una imagen más ligera de OpenJDK para correr la aplicación
FROM openjdk:17-jdk-alpine

# Crea un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado desde la fase anterior al contenedor
COPY --from=build /app/target/procedimientosrp-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que correrá la aplicación Spring Boot (ej. 8081)
EXPOSE 8081

# Comando para ejecutar la aplicación con soporte para TLS 1.0, 1.1 y 1.2
ENTRYPOINT ["java", "-Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2", "-jar", "app.jar"]