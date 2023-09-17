# You can change this base image to anything else
# But make sure to use the correct version of Java
FROM openjdk:17-oracle

# Simply the artifact path
#ARG artifact=target/weatherPrediction-0.0.1-SNAPSHOT.war

#WORKDIR /opt/app

#COPY ${artifact} weather.war

# This should not be changed
#ENTRYPOINT ["java","-jar","weather.war"]

#EXPOSE 8083

WORKDIR /opt/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]