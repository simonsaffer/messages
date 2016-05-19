package com.messages.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import com.google.common.collect.ImmutableList;
import com.messages.core.Point;
import com.messages.util.ConfigUtil;


public abstract class PointKafkaConsumer implements Runnable {

  private static final String POINTS_TOPIC = "points";
  private final KafkaConsumer<String, Point> consumer;
  private final int id;

  public PointKafkaConsumer(int id) {
    this.id = id;
    consumer = new KafkaConsumer<String, Point>(ConfigUtil.getKafkaProperties());
  }

  @Override
  public void run() {

    try {
      consumer.subscribe(ImmutableList.of(POINTS_TOPIC));

      while (true) {
        ConsumerRecords<String, Point> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, Point> record : records) {
          Map<String, Object> data = new HashMap<>();
          data.put("partition", record.partition());
          data.put("offset", record.offset());
          data.put("value", record.value());
          System.out.println(this.id + ": " + data);

          process(record.value());
        }
      }
    } catch (WakeupException e) {
      // ignore for shutdown
    } finally {
      consumer.close();
    }
  }

  public void shutdown() {
    consumer.wakeup();
  }

  protected abstract void process(Point point);
}
