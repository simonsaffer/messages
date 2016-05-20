package com.messages;

import org.skife.jdbi.v2.DBI;

import com.messages.api.PointResource;
import com.messages.db.PointDAO;
import com.messages.kafka.KafkaConsumerApplication;
import com.messages.kafka.PointProducer;
import com.messages.websocket.PointEndpoint;

import be.tomcools.dropwizard.websocket.WebsocketBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MessagesApplication extends Application<MessagesConfiguration> {

  private WebsocketBundle websocket = new WebsocketBundle();

  public static void main(final String[] args) throws Exception {
    new MessagesApplication().run(args);
  }

  @Override
  public String getName() {
    return "messages";
  }

  @Override
  public void initialize(final Bootstrap<MessagesConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/", "assets/index.html"));
    bootstrap.addBundle(websocket);
  }

  @Override
  public void run(final MessagesConfiguration config,
                  final Environment environment) {

    // DB
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
    final PointDAO dao = jdbi.onDemand(PointDAO.class);
    final PointProducer pointProducer = new PointProducer();

    // REST
    environment.jersey().register(new PointResource(dao, pointProducer));

    // Kafka
    environment.lifecycle().manage(pointProducer);
    KafkaConsumerApplication consumerApplication = new KafkaConsumerApplication();
    environment.lifecycle().manage(consumerApplication);

    // Websocket
    websocket.addEndpoint(PointEndpoint.class);
  }

}
