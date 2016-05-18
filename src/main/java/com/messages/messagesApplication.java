package com.messages;

import org.skife.jdbi.v2.DBI;

import com.messages.api.PointResource;
import com.messages.db.PointDAO;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MessagesApplication extends Application<MessagesConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MessagesApplication().run(args);
    }

    @Override
    public String getName() {
        return "messages";
    }

    @Override
    public void initialize(final Bootstrap<MessagesConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MessagesConfiguration config,
                    final Environment environment) {

      final DBIFactory factory = new DBIFactory();
      final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
      final PointDAO dao = jdbi.onDemand(PointDAO.class);
      environment.jersey().register(new PointResource(dao));
    }

}
