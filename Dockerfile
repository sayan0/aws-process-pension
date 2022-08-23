FROM openjdk:8
EXPOSE 5500
ADD target/*.jar aws-process-pension.jar
ENTRYPOINT [ "java" , "-jar" , "/aws-process-pension.jar"]