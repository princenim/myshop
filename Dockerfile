FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/shop-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]