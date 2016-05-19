package com.messages.kafka;

import static org.junit.Assert.assertEquals;
import static net.java.quickcheck.QuickCheck.forAll;

import org.junit.Test;

import com.messages.core.Point;
import com.messages.testutil.PointGenerator;

import net.java.quickcheck.characteristic.AbstractCharacteristic;

public class PointSerializerTest {

  @Test
  public void testSerializeAndDeserialize() throws Exception {

    PointSerializer serializer = new PointSerializer();
    PointDeserializer deserializer = new PointDeserializer();

    forAll(new PointGenerator(), new AbstractCharacteristic<Point>() {
      @Override
      protected void doSpecify(Point point) throws Throwable {
        System.out.println("Tested with input: " + point.toString());
        byte[] serializedPoint = serializer.serialize("", point);
        Point deserializedPoint = deserializer.deserialize("", serializedPoint);
        assertEquals(point, deserializedPoint);
      }
    });

  }
}
