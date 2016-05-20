package com.messages.websocket;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messages.core.Point;

public class PointDecoder implements Decoder.Text<Point> {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public PointDecoder() {
  }

  @Override
  public Point decode (String src) throws DecodeException {
    try {
      return objectMapper.readValue(src, Point.class);
    } catch (IOException e) {
      throw new DecodeException(src, "Unable to decode", e);
    }
  }

  @Override
  public boolean willDecode (String src) {
    return true; // Decode all points
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}
