package com.messages.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messages.core.Point;

public class PointEncoder implements Encoder.Text<Point> {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public PointEncoder() {
  }

  @Override
  public void init(EndpointConfig endpointConfig) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public String encode(Point point) throws EncodeException {
    try {
      return objectMapper.writeValueAsString(point);
    } catch (JsonProcessingException e) {
      throw new EncodeException(point, "Unable to encode", e);
    }
  }
}
