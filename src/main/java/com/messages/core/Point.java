package com.messages.core;

import java.io.Serializable;

public class Point implements Serializable {
  private int x;
  private int y;

  public Point() {
  }

  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Point{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }
}
