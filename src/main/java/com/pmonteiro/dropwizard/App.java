package com.pmonteiro.dropwizard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pmonteiro.dropwizard.db.PersistInitialiser;
import com.pmonteiro.dropwizard.resources.TasksResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class App extends Application<AppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardGuiceMongoDB";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<AppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment) {
        final Injector injector = Guice.createInjector(new AppModule(configuration, environment));
        environment.jersey().register(injector.getInstance(TasksResource.class));
        injector.getInstance(PersistInitialiser.class);
    }

}
