package com.messages.kafka;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import javax.websocket.CloseReason;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.SendHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.messages.core.Point;

public class PointWebSocketConsumerTest {

  @Test
  public void testProcess() throws Exception {

    PointWebSocketConsumer consumer = new PointWebSocketConsumer(1);
    FakeAsync remote = new FakeAsync();
    consumer.addRemote(new FakeSession(remote) {
    });

    assertEquals(0, remote.getPoints().size());
    consumer.process(new Point(1,2));
    assertEquals(1, remote.getPoints().size());
    assertEquals(ImmutableList.of(new Point(1,2)), remote.getPoints());
    consumer.process(new Point(3,4));
    assertEquals(2, remote.getPoints().size());
    assertEquals(ImmutableList.of(new Point(1,2), new Point(3,4)), remote.getPoints());

  }

  private class FakeSession implements Session {

    private final RemoteEndpoint.Async remote;

    private FakeSession(RemoteEndpoint.Async remote) {
      this.remote = remote;
    }

    @Override
    public WebSocketContainer getContainer() {
      return null;
    }

    @Override
    public void addMessageHandler(MessageHandler messageHandler) throws IllegalStateException {

    }

    @Override
    public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Whole<T> whole) {

    }

    @Override
    public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Partial<T> partial) {

    }

    @Override
    public Set<MessageHandler> getMessageHandlers() {
      return null;
    }

    @Override
    public void removeMessageHandler(MessageHandler messageHandler) {

    }

    @Override
    public String getProtocolVersion() {
      return null;
    }

    @Override
    public String getNegotiatedSubprotocol() {
      return null;
    }

    @Override
    public List<Extension> getNegotiatedExtensions() {
      return null;
    }

    @Override
    public boolean isSecure() {
      return false;
    }

    @Override
    public boolean isOpen() {
      return false;
    }

    @Override
    public long getMaxIdleTimeout() {
      return 0;
    }

    @Override
    public void setMaxIdleTimeout(long l) {

    }

    @Override
    public void setMaxBinaryMessageBufferSize(int i) {

    }

    @Override
    public int getMaxBinaryMessageBufferSize() {
      return 0;
    }

    @Override
    public void setMaxTextMessageBufferSize(int i) {

    }

    @Override
    public int getMaxTextMessageBufferSize() {
      return 0;
    }

    @Override
    public RemoteEndpoint.Async getAsyncRemote() {
      return remote;
    }

    @Override
    public RemoteEndpoint.Basic getBasicRemote() {
      return null;
    }

    @Override
    public String getId() {
      return "";
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void close(CloseReason closeReason) throws IOException {

    }

    @Override
    public URI getRequestURI() {
      return null;
    }

    @Override
    public Map<String, List<String>> getRequestParameterMap() {
      return null;
    }

    @Override
    public String getQueryString() {
      return null;
    }

    @Override
    public Map<String, String> getPathParameters() {
      return null;
    }

    @Override
    public Map<String, Object> getUserProperties() {
      return null;
    }

    @Override
    public Principal getUserPrincipal() {
      return null;
    }

    @Override
    public Set<Session> getOpenSessions() {
      return null;
    }
  }

  private class FakeAsync implements RemoteEndpoint.Async {

    private final List<Point> points = new ArrayList<>();

    @Override
    public long getSendTimeout() {
      return 0;
    }

    @Override
    public void setSendTimeout(long l) {

    }

    @Override
    public void sendText(String s, SendHandler sendHandler) {

    }

    @Override
    public Future<Void> sendText(String s) {
      return null;
    }

    @Override
    public Future<Void> sendBinary(ByteBuffer byteBuffer) {
      return null;
    }

    @Override
    public void sendBinary(ByteBuffer byteBuffer, SendHandler sendHandler) {

    }

    @Override
    public Future<Void> sendObject(Object o) {
      points.add((Point) o);
      return null;
    }

    @Override
    public void sendObject(Object o, SendHandler sendHandler) {

    }

    @Override
    public void setBatchingAllowed(boolean b) throws IOException {

    }

    @Override
    public boolean getBatchingAllowed() {
      return false;
    }

    @Override
    public void flushBatch() throws IOException {

    }

    @Override
    public void sendPing(ByteBuffer byteBuffer) throws IOException, IllegalArgumentException {

    }

    @Override
    public void sendPong(ByteBuffer byteBuffer) throws IOException, IllegalArgumentException {

    }

    public List<Point> getPoints() {
      return points;
    }
  }


}
