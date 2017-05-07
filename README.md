# Dropwizard Guice and MongoDB (JPA) application

This a dockerized RESTful Dropwizard application, to provide an example on how to integrate MongoDB and JPA in Dropwizard, using dependency injection.
This is using a
Instead of using Morphia, MongoJack or MongoDB Java driver the decision was to investigate how to 
integrate MongoDB with JPA. Please read more about [Hibernate OGM Reference Guide](https://docs.jboss.org/hibernate/stable/ogm/reference/en-US/html_single)
The example is a simple RESTful interface to easily manage a task planner.

- using [Dropwizard](https://dropwizard.github.io/dropwizard/) v1.1.0 framework
- dependency injection achieve through [Google Guice](https://code.google.com/p/google-guice/)
- JPA implementation through [Hibernate OGM](https://docs.jboss.org/hibernate/stable/ogm/reference/en-US/html_single)
- [FongoDB](https://github.com/fakemongo/fongo) is an in-memory java implementation of MongoDB, intercepting call to the standard mongo-java-driver
- API documentation provided by [swagger.io](http://swagger.io//) and [swagger UI](http://swagger.io/swagger-ui/)

How to start the DropwizardGuice application
---

1. Run `mvn clean package` to build your application
1. Start application with `java -jar target/dropwizard-guice-1.0-SNAPSHOT-uber.jar server target/config.yml`
1. To check that the application is running enter url `http://localhost:8080`
1. To interact with the application using Swagger UI endpoints enter url `http://localhost:8080/swagger`