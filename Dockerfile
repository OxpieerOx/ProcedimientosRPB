# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Crea un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR de tu aplicación al contenedor
COPY target/procedimientosrp-0.0.1-SNAPSHOT.jar app.jar
                                                    
# Expone el puerto en el que correrá la aplicación Spring Boot (ej. 8081)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
