package com.messages.websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.messages.kafka.PointWebSocketConsumer;

@ServerEndpoint(value="/pointws", encoders = PointEncoder.class, decoders= PointDecoder.class)
public class PointEndpoint {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  @OnOpen
  public void onOpen(Session session) {
    logger.info("Connected ... " + session.getId());
    PointWebSocketConsumer.addRemote(session);
  }

  @OnMessage
  public String onMessage(String message, Session session) {
    switch (message) {
      case "quit":
        try {
          session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game ended"));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
    }
    return message;
  }

  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
  }

}
