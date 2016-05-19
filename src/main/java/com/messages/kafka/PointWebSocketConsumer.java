package com.messages.kafka;

import com.messages.core.Point;

public class PointWebSocketConsumer extends PointKafkaConsumer {

  public PointWebSocketConsumer(int id) {
    super(id);
  }

  @Override
  protected void process(Point point) {
    System.out.println("Nooo " + point);
  }
}
