# Stage 1 — Build the application
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2 — Create lightweight image
FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app

# JVM Optimization Flags – Production Recommended
ENV JAVA_OPTS="-Xms128m -Xmx256m \
               -XX:MaxMetaspaceSize=128m \
               -XX:+UseG1GC \
               -Dserver.tomcat.threads.max=0 \
               -XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75"

# Copy JAR from build stage
COPY --from=build /app/target/*.jar order-service-0.0.1-SNAPSHOT.jar

# Expose port (change per service)
EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar order-service-0.0.1-SNAPSHOT.jar"]
