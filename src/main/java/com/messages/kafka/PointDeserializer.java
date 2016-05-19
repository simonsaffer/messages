package com.messages.kafka;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.messages.core.Point;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class PointDeserializer implements Deserializer<Point> {
  @Override
  public void configure(Map<String, ?> map, boolean b) {

  }

  @Override
  public Point deserialize(String s, byte[] bytes) {
    ByteInputStream input = new ByteInputStream(bytes, bytes.length);
    Point point = new Point(input.read(), input.read());
    try {
      input.close();
    } catch (IOException e) {
    }
    return point;
  }

  @Override
  public void close() {

  }
}
