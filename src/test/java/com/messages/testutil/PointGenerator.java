package com.messages.testutil;

import static net.java.quickcheck.generator.PrimitiveGenerators.integers;

import com.messages.core.Point;

import net.java.quickcheck.Generator;

public class PointGenerator implements Generator<Point> {

  @Override
  public Point next() {
    Generator<Integer> integers = integers();
    return new Point(integers.next(), integers.next());
  }
}
