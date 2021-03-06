package com.pmonteiro.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pmonteiro.dropwizard.db.MongoConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;

public class AppConfiguration extends io.dropwizard.Configuration {

    @Valid
    @JsonProperty("mongo")
    private MongoConfiguration databaseConfiguration;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    MongoConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
}
