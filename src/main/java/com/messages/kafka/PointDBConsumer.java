package com.messages.kafka;

import com.messages.core.Point;

public class PointDBConsumer extends PointKafkaConsumer {

  public PointDBConsumer(int id) {
    super(id);
  }

  @Override
  protected void process(Point point) {
    System.out.println("Yeah! " + point);
  }
}
