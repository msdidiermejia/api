FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

#COPY ./jt400.jar /usr/local/tomcat/lib/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]