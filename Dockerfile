FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 🔥 dá permissão para o mvnw
RUN chmod +x mvnw

# builda o projeto
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]