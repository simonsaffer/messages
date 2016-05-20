package com.messages.kafka;

import com.messages.core.Point;
import com.messages.db.PointDAO;

public class PointDBConsumer extends PointKafkaConsumer {

  private static final String DB_GROUP = "dbgroup";

  private final PointDAO pointDAO;

  public PointDBConsumer(int id, PointDAO pointDAO) {
    super(id, DB_GROUP);
    this.pointDAO = pointDAO;
  }

  @Override
  protected void process(Point point) {
    pointDAO.insert(point.getX(), point.getY());
  }
}
