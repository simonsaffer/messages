package com.messages.kafka;

import java.util.Optional;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.messages.core.Point;
import com.messages.util.ConfigUtil;

import io.dropwizard.lifecycle.Managed;

public class PointProducer implements Managed {

  final static Logger logger = LoggerFactory.getLogger(PointProducer.class);

  private static final String TOPIC = "points";
  private KafkaProducer<String, Point> producer;

  public PointProducer() {

  }


  @Override
  public void start() throws Exception {
    Optional<Properties> properties = ConfigUtil.readPropertiesFile(ConfigUtil.KAFKA_PROPERTIES);

    properties.ifPresent(props -> producer = new KafkaProducer(props));

    if(!properties.isPresent()) {
      logger.error("Failed to start producer");
    }
  }

  @Override
  public void stop() throws Exception {
    producer.close();
  }

  public void addNewPoint(Point point) {
    producer.send(new ProducerRecord<String, Point>(TOPIC, point));
  }
}
