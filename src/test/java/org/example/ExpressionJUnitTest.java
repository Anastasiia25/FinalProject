package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

class ExpressionJUnitTest {

      // 1. Test for division by zero or negative number
    @Test
    void testZeroInputThrowsException() {
      
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionJUnit.calculateRandomSum(0);
        }, "If N=0, an exception should be thrown.");

        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionJUnit.calculateRandomSum(-1);
        }, "If N=-1, an exception should be thrown.");
    }
    
    // Check that the average value in the range from 1 to 100
    @Test
    void testResultIsWithinExpectedRange() {
        int testN = 99;
        double result = ExpressionJUnit.calculateRandomSum(testN); //

        int minExpectedSum = testN * 1;    // 99
        int maxExpectedSum = testN * 100;   // 9900

        assertTrue(result >= minExpectedSum, "The sum should be >= " + minExpectedSum);
        assertTrue(result <= maxExpectedSum, "The sum should be <= " + maxExpectedSum);
    }
}
