FROM maven:3.8.5-openjdk-17-slim

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean install

WORKDIR /app/target

CMD ["java", "-jar", "syntax-core.jar"]
