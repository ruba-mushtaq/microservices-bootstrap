FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY target/cart-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", \
	"-Xms128m", \
	"-Xmx256m", \
	"-XX:+UseG1GC", \
	"-XX:MaxMetaspaceSize=128m", \
	"-jar", "app.jar"]
