FROM openjdk:10.0.1-jre
ADD build/libs/demo-0.0.1-SNAPSHOT.jar /usr/app.jar

WORKDIR /usr
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]