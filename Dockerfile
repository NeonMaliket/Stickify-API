FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
ENV BOT_TOKEN=""
ENV BOT_NAME=""
ENV OPENAI_API_KEY=""
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080 443 8443 80
ENTRYPOINT ["java", "-jar", "app.jar"]


