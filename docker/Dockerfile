FROM openjdk:16-jdk-alpine
WORKDIR ..
RUN pwd
COPY target/audit-0.0.1-SNAPSHOT.jar audit.jar
ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-jar", "audit.jar", "--spring.profiles.active=${ADDITIONAL_APP_PROFILES:local}"]
