# --- Etape 1 : build du WAR avec Maven ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q clean package -DskipTests

# --- Etape 2 : image d'execution Tomcat 9 (API javax) ---
FROM tomcat:9.0-jre17-temurin
# Nettoie les webapps par defaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Deploie l'application a la racine (context path "/")
COPY --from=build /app/target/diti4_spring_mvc.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
