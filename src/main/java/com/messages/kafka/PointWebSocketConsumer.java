package com.messages.kafka;

import com.messages.core.Point;

public class PointWebSocketConsumer extends PointKafkaConsumer {

  private static final String WEB_SOCKET_GROUP = "wsgroup";

  public PointWebSocketConsumer(int id) {
    super(id, WEB_SOCKET_GROUP);
  }

  @Override
  protected void process(Point point) {
    System.out.println("Nooo " + point);
  }
}
