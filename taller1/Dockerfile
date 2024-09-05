# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por tu aplicación en el contenedor
COPY target/Taller1-1.0.0.jar /app/Taller1-1.0.0.jar

COPY target/classes /app/classes
COPY src/main/resources/templates /app/templates
COPY src/main/resources/static /app/static

# Expone el puerto en el que la aplicación está configurada para ejecutarse
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "Taller1-1.0.0.jar"]
