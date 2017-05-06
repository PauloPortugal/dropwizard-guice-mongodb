package com.pmonteiro.dropwizard.db;

import com.pmonteiro.dropwizard.AppConfiguration;

import javax.validation.constraints.NotNull;

public class DatabaseConfiguration extends AppConfiguration {

    @NotNull
    private String host;

    @NotNull
    private String port;

    @NotNull
    private String db;

    @NotNull
    private String collection;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getCollection() {
        return collection;
    }
}
