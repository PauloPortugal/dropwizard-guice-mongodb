# Dropwizard Guice and MongoDB (JPA) application

This is a dockerized RESTful Dropwizard application, to provide an example on how to integrate MongoDB and JPA in Dropwizard, using dependency injection.
This is using a 
Instead of using Morphia, MongoJack or MongoDB Java driver the decision was to investigate how to 
integrate MongoDB with JPA. Please read more about [Hibernate OGM Reference Guide](https://docs.jboss.org/hibernate/stable/ogm/reference/en-US/html_single)
The example is a simple RESTful interface to easily manage a task planner.

- using [Dropwizard](https://dropwizard.github.io/dropwizard/) v1.1.0 framework
- dependency injection achieve through [Google Guice](https://code.google.com/p/google-guice/)
- JPA implementation through [Hibernate OGM](https://docs.jboss.org/hibernate/stable/ogm/reference/en-US/html_single)
- [FongoDB](https://github.com/fakemongo/fongo) is an in-memory java implementation of MongoDB, intercepting call to the standard mongo-java-driver
- API documentation provided by [swagger.io](http://swagger.io//) and [swagger UI](http://swagger.io/swagger-ui/)
- [Docker](https://www.docker.com/) as the containerisation solution

How to start the DropwizardGuice application
---

1. Run `mvn clean package` to build your application
1. Start application with `java -jar target/dropwizard-guice-1.0-SNAPSHOT-uber.jar server target/config.yml`
1. To check that the application is running enter url `http://localhost:8080`
1. To interact with the application using Swagger UI endpoints enter url `http://localhost:8080/swagger`
1. To run the application inside a docker container, `./docker-run.sh`


<a name="paths"></a>
## API Paths

<a name="create"></a>
### Creates a new task
```
POST /tasks
```


#### Description
Creates a new task. Task descriptions are not unique.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**body**  <br>*required*|payload|[A new task](#a-new-task)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**201**|Created|[A new task](#a-new-task)|


#### Consumes

* `application/json`


#### Produces

* `application/json`


<a name="gettasks"></a>
### Get all the tasks
```
GET /tasks
```


#### Description
Returns all the tasks save on the database


#### Responses

|HTTP Code|Schema|
|---|---|
|**200**|< [Task Entity](#task-entity) > array|


#### Produces

* `application/json`


<a name="gettask"></a>
### Get task by id
```
GET /tasks/{taskId}
```


#### Description
Returns task by Id. If it does not exist it will return a HTTP 404


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**taskId**  <br>*required*|taskId|string(uuid)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**||[Task Entity](#task-entity)|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


<a name="update"></a>
### Updates task by id
```
PUT /tasks/{taskId}
```


#### Description
Updates a task description if available in the database


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**taskId**  <br>*required*|taskId|string(uuid)|
|**Body**|**body**  <br>*required*|payload|[A new task](#a-new-task)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Updated|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


<a name="delete"></a>
### Deletes task by id
```
DELETE /tasks/{taskId}
```


#### Description
Deletes a if available in the database


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**taskId**  <br>*required*|taskId|string(uuid)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**204**|No Content|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`