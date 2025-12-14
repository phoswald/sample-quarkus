FROM code.phoswald.ch/philip/jre-21:latest

WORKDIR /usr/local/application
COPY target/quarkus-app /usr/local/application
CMD ["java", "-jar", "quarkus-run.jar"]

USER daemon:daemon
EXPOSE 8080
