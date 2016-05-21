package com.messages.testutil;

import static org.junit.Assert.assertNotEquals;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

public abstract class EqualsAndHashCodeTester<T> {

  @Test
  public final void testEqualsAndHashCode() throws Exception {

    // Test that two equal objects produce the same hash code
    T A = getEqualInstanceA();
    T B = getEqualInstanceB();

    assertTrue(A.equals(B));
    assertEquals(A.hashCode(), B.hashCode());

    // Test that for non equal instances .equals returns false and hashCode returns different values
    // We can't guarantee no collisions but this test aims to ensure that collisions will be rare
    // If this fails and you're sure that hashCode() is ok, then just change the objects returned in getUnequalInstances()
    T[] unEqualContents = getUnequalInstances();
    // Compare all experiences in unEqualExperiences to each other
    for (int i = 0; i < unEqualContents.length; i++) {
      for (int j = i+1; j < unEqualContents.length; j++) {

        // Try to assure that hashCode is not random
        assertEquals(unEqualContents[i].hashCode(), unEqualContents[i].hashCode());
        assertEquals(unEqualContents[j].hashCode(), unEqualContents[j].hashCode());

        // Test that for unequal objects .equals returns false
        assertNotEquals(unEqualContents[i] + " should not equal " + unEqualContents[j], unEqualContents[i], unEqualContents[j]);
        // Test that for unequal objects .hashCode returns different results (at least in the cases used in this test)
        assertNotEquals(unEqualContents[i] + " and " + unEqualContents[j] + " should generate unique hash codes",
          unEqualContents[i].hashCode(), unEqualContents[j].hashCode());
      }
    }

  }

  protected abstract T[] getUnequalInstances();

  protected abstract T getEqualInstanceB();

  protected abstract T getEqualInstanceA();
}

