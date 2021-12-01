FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/cloudrun-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} democloud.jar
ENTRYPOINT ["java","-jar","democloud.jar"]