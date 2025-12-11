# 1: BUILD 

FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# TEST
RUN mvn clean install -DskipTests=true


# 2: RUNTIME 
FROM eclipse-temurin:21
WORKDIR /app

# 3. Copy JAR files
COPY --from=build /app/target/ExpressionJUnit-1.0-SNAPSHOT.jar app.jar

# 4. Run app
EXPOSE 8080 
ENTRYPOINT ["java", "-cp", "app.jar", "org.example.ExpressionWebServer"]