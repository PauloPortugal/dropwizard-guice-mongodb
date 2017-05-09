#!/bin/bash
mvn -DskipTests clean package && \
docker build -t pauloportugal/dropwizard-guice-mongodb:local . && \
docker run -ti --rm --name guice-jpa pauloportugal/dropwizard-guice-mongodb:local