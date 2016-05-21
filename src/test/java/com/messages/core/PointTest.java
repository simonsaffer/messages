package com.messages.core;

import com.messages.testutil.EqualsAndHashCodeTester;

public class PointTest extends EqualsAndHashCodeTester<Point> {

  @Override
  protected Point[] getUnequalInstances() {
    return new Point[] {
      new Point(0,0),
      new Point(-3,0),
      new Point(0, 1),
      new Point(Integer.MAX_VALUE, Integer.MIN_VALUE)
    };
  }

  @Override
  protected Point getEqualInstanceB() {
    return new Point(123, 456);
  }

  @Override
  protected Point getEqualInstanceA() {
    return new Point(123, 456);
  }
}
