# Usa una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /FES

# Copia el JAR compilado al contenedor
COPY target/*.jar FES-0.0.1-SNAPSHOT.jar.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar tu app
ENTRYPOINT ["java", "-jar", "FES-0.0.1-SNAPSHOT.jar.jar"]
