package com.pmonteiro.dropwizard;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.pmonteiro.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.setup.Environment;

import java.util.Properties;

public class AppModule extends AbstractModule {

    private AppConfiguration configuration;
    private Environment environment;

    public AppModule(final AppConfiguration configuration, final Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Override
    protected void configure() {
        bind(AppConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);

        install(jpaModule(configuration.getDatabaseConfiguration()));
    }

    private Module jpaModule(DatabaseConfiguration databaseConfiguration ) {
        final Properties properties = new Properties();
        properties.put("eclipselink.target-database", "org.eclipse.persistence.nosql.adapters.mongo.MongoPlatform");
        properties.put("eclipselink.nosql.connection-spec", "org.eclipse.persistence.nosql.adapters.mongo.MongoConnectionSpec");
        properties.put("eclipselink.logging.level", "FINEST");
        properties.put("eclipselink.nosql.property.mongo.host", databaseConfiguration.getHost());
        properties.put("eclipselink.nosql.property.mongo.port", databaseConfiguration.getPort());
        properties.put("eclipselink.nosql.property.mongo.db", databaseConfiguration.getDb());

        final JpaPersistModule jpaModule = new JpaPersistModule("DefaultUnit");
        jpaModule.properties(properties);

        return jpaModule;
    }
}
