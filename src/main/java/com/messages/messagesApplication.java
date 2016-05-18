package com.messages;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class messagesApplication extends Application<messagesConfiguration> {

    public static void main(final String[] args) throws Exception {
        new messagesApplication().run(args);
    }

    @Override
    public String getName() {
        return "messages";
    }

    @Override
    public void initialize(final Bootstrap<messagesConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final messagesConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
