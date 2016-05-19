package com.messages.kafka;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.messages.core.Point;


public class PointSerializer implements Serializer<Point> {
  @Override
  public void configure(Map<String, ?> map, boolean b) {

  }

  @Override
  public byte[] serialize(String s, Point point) {
    return ByteBuffer.allocate(Integer.BYTES*2)
      .putInt(point.getX())
      .putInt(point.getY())
      .array();
  }

  @Override
  public void close() {

  }
}
