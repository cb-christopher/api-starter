ARG AWS_ACCOUNT_ID
FROM $AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/openjdk:8 as builder
WORKDIR /api-starter/
COPY ./ /api-starter/
RUN ./gradlew clean build -x test

FROM $AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/openjdk:8-jdk-alpine
COPY --from=builder /api-starter/build/libs/api-starter-0.0.1-SNAPSHOT.jar ./api-starter.jar
ENTRYPOINT ["java", "-jar","-Xmx512m","-Xms256m","/api-starter.jar"]