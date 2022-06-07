FROM openjdk:11

#ENV JAVA_HOME=/usr/app/
#ENV JAVA_HOME=/usr/

#WORKDIR $APP_HOME

COPY build/libs/*.jar ./application.jar

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "application.jar"]


