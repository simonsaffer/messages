package com.messages.kafka;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.skife.jdbi.v2.sqlobject.Bind;

import com.google.common.collect.ImmutableList;
import com.messages.core.Point;
import com.messages.db.PointDAO;

public class PointDBConsumerTest {

  @Test
  public void testProcess() throws Exception {

    PointDAO pointDAO = new PointDAO() {

      private List<Point> points = new ArrayList<>();
      private int id = 0;

      @Override
      public void createPointTableIfNotExists() {
      }

      @Override
      public long insert(@Bind("x") int x, @Bind("y") int y) {
        points.add(new Point(x, y));
        return id++;
      }

      @Override
      public List<Point> getAllPoints() {
        return points;
      }
    };
    PointDBConsumer consumer = new PointDBConsumer(1, pointDAO);

    assertEquals(0, pointDAO.getAllPoints().size());
    consumer.process(new Point(1,2));
    assertEquals(1, pointDAO.getAllPoints().size());
    assertEquals(ImmutableList.of(new Point(1,2)), pointDAO.getAllPoints());
    consumer.process(new Point(3,4));
    assertEquals(2, pointDAO.getAllPoints().size());
    assertEquals(ImmutableList.of(new Point(1,2), new Point(3,4)), pointDAO.getAllPoints());

  }
}
