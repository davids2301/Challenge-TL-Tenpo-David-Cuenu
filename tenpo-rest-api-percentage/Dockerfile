FROM maven:3.6.3-jdk-11

ARG JAVA_OPTS=-Dspring-boot.run.jvmArguments="-Xms256m -Xmx512m"
ENV JAVA_OPTS ${JAVA_OPTS}

ARG SERVER_PORT=${SERVER_PORT}

WORKDIR /app
COPY . .

RUN mvn clean install
RUN mvn package spring-boot:repackage
RUN mvn package

EXPOSE ${SERVER_PORT}

ENTRYPOINT exec java $JAVA_OPTS -jar infrastructure/target/percentage-infrastructure-1.0.0.jar