FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-XX:+UseG1GC", "-XX:MaxMetaspaceSize=128m", "-jar", "app.jar"]
