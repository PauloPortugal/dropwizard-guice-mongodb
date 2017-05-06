package com.pmonteiro.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pmonteiro.dropwizard.db.DatabaseConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;

public class AppConfiguration extends io.dropwizard.Configuration {

    @Valid
    @JsonProperty("database")
    private DatabaseConfiguration databaseConfiguration;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
}
