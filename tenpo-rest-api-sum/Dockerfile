FROM maven:3.6.3-jdk-11

WORKDIR /app
COPY . .

RUN mvn clean install
RUN mvn package spring-boot:repackage
RUN mvn package

EXPOSE ${SERVER_PORT}

ENTRYPOINT exec java $JAVA_OPTS -jar infrastructure/target/sum-infrastructure-1.0.0.jar
