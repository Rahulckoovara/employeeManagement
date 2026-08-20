FROM eclipse-temurin
COPY target/doc-demo.jar /doc-demo.jar
ENTRYPOINT ["java", "-jar", "/doc-demo.jar"]

