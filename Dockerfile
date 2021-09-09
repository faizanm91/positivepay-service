FROM openjdk:8-jdk-alpine
RUN echo cd
ARG JAR_FILE=target/positivepay-service-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
# Expose HTTP port
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
