FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/flashseat-0.0.1-SNAPSHOT.jar app.jar
COPY aiven-ca.pem /app/aiven-ca.pem

RUN keytool -importcert \
    -noprompt \
    -trustcacerts \
    -alias aiven-ca \
    -file /app/aiven-ca.pem \
    -keystore /opt/java/openjdk/lib/security/cacerts \
    -storepass changeit

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]