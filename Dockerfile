FROM openjdk:11

#ENV APP_HOME=/usr/app/

COPY build/libs/*.jar ./application.jar

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "application.jar"]


