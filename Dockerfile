FROM openjdk:11
ARG JAR_FILE=build/libs/*.JAR_FILE
COPY ${JAR_FILE} app.JAR_FILE

EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","/app.jar"]
