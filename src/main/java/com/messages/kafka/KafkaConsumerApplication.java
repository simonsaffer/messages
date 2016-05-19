package com.messages.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerApplication {
  private static final String TOPIC = "points";

  public static void main(String[] args) {
    int numConsumers = 3;
    String groupId = "pointgroup";
    List<String> topics = Arrays.asList(TOPIC);
    ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

    final List<PointKafkaConsumer> consumers = new ArrayList<>();
    for (int i = 0; i < numConsumers; i++) {
      PointKafkaConsumer consumer;
      if (i % 2 == 0) consumer = new PointDBConsumer(i);
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
}
