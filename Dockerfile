FROM openjdk:17-jdk-alpine3.13

COPY target/java-spring-0.0.1-SNAPSHOT.jar java-spring-1.0.0.jar

ENTRYPOINT ["java","-jar","/java-spring-1.0.0.jar"]