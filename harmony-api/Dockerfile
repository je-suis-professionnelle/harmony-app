FROM maven:3.9.6-sapmachine-17
WORKDIR /app
COPY . .
RUN mvn package
RUN mvn clean install
EXPOSE 8080
CMD ["java", "-jar", "target/harmony-api-0.0.1-SNAPSHOT.jar"]