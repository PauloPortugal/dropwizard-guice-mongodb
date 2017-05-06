package com.pmonteiro.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardGuiceMongoDBApplication extends Application<DropwizardGuiceMongoDBConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardGuiceMongoDBApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardGuiceMongoDB";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardGuiceMongoDBConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardGuiceMongoDBConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
