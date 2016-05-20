package com.messages.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.messages.db.PointDAO;

import io.dropwizard.lifecycle.Managed;

public class KafkaConsumerApplication implements Managed {
  private static final String TOPIC = "points";

  private final PointDAO pointDAO;

  public KafkaConsumerApplication(PointDAO dao) {
    this.pointDAO = dao;
  }

  @Override
  public void start() {
    int numConsumers = 3;
    ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

    final List<PointKafkaConsumer> consumers = new ArrayList<>();
    for (int i = 0; i < numConsumers; i++) {
      PointKafkaConsumer consumer;
      if (i % 2 == 0) consumer = new PointDBConsumer(i, pointDAO);
      else consumer = new PointWebSocketConsumer(i);
      consumers.add(consumer);
      executor.submit(consumer);
    }

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        for (PointKafkaConsumer consumer : consumers) {
          consumer.shutdown();
        }
        executor.shutdown();
        try {
          executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public void stop() throws Exception {

  }
}
