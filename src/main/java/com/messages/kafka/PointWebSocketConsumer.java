package com.messages.kafka;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.messages.core.Point;

public class PointWebSocketConsumer extends PointKafkaConsumer {

  private static final Map<String, RemoteEndpoint.Async> remotes = new ConcurrentHashMap<>();

  private static final String WEB_SOCKET_GROUP = "wsgroup";

  public PointWebSocketConsumer(int id) {
    super(id, WEB_SOCKET_GROUP);
    try {
      Server server = new Server(8080);
      WebSocketHandler wsHandler = new WebSocketHandler() {
        @Override
        public void configure(WebSocketServletFactory factory) {
          factory.register(WebSocketHandler.class);
        }
      };
      server.setHandler(wsHandler);
      server.start();
      server.join();
    } catch (Exception e) {

    }
  }

  public static void addRemote(Session session) {
    remotes.put(session.getId(), session.getAsyncRemote());
  }

  @Override
  protected void process(Point point) {
    logger.info("Sent point " + point);
    remotes.entrySet().stream().forEach(remote -> {
      remote.getValue().sendObject(point);
    });
  }
}
