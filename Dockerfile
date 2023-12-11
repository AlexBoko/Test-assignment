FROM openjdk:17-jdk

WORKDIR /app

COPY target/Test-assignment-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/socks_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=Wertyrwer
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=validate
ENV SPRING_LIQUIBASE_CHANGE_LOG=classpath:/db/changelog/db.changelog-master.yaml

CMD ["java", "-jar", "/app/app.jar"]
