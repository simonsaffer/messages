package com.messages.kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.messages.core.Point;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;


public class PointSerializer implements Serializer<Point> {
  @Override
  public void configure(Map<String, ?> map, boolean b) {

  }

  @Override
  public byte[] serialize(String s, Point point) {
    ByteOutputStream output = new ByteOutputStream();
    output.write(point.getX());
    output.write(point.getY());
    byte[] bytes = output.getBytes();
    output.close();
    return bytes;
  }

  @Override
  public void close() {

  }
}
