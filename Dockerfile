# --------Stage1: Building the app--------
FROM gradle:8.5-jdk21 AS builder
WORKDIR /CloudBasedAppsHW1


# Copying and installing dependencies

COPY CloudBasedAppsHW1/build.gradle.kts CloudBasedAppsHW1/settings.gradle.kts CloudBasedAppsHW1/gradlew ./
COPY CloudBasedAppsHW1/gradle/ ./gradle/

RUN ./gradlew --no-daemon dependencies

# Building the rest of the project

COPY CloudBasedAppsHW1/ ./
RUN ./gradlew --no-daemon bootJar

# --------Stage2: Running the app--------

FROM eclipse-temurin:21-jre

WORKDIR /CloudBasedAppsHW1

COPY --from=builder /CloudBasedAppsHW1/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]