package com.messages.kafka;

import com.messages.core.Point;

public class PointDBConsumer extends PointKafkaConsumer {

  private static final String DB_GROUP = "dbgroup";

  public PointDBConsumer(int id) {
    super(id, DB_GROUP);
  }

  @Override
  protected void process(Point point) {
    System.out.println("Yeah! " + point);
  }
}
