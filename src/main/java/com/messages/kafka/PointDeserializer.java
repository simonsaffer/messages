package com.messages.kafka;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.messages.core.Point;

public class PointDeserializer implements Deserializer<Point> {
  @Override
  public void configure(Map<String, ?> map, boolean b) {

  }

  @Override
  public Point deserialize(String s, byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    return new Point(byteBuffer.getInt(), byteBuffer.getInt());
  }

  @Override
  public void close() {

  }
}
