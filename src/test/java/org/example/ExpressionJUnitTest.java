package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

class ExpressionJUnitTest {

      // 1. Test for division by zero or negative number
    @Test
    void testZeroInputThrowsException() {
      
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionJUnit.calculateAverage(0);
        }, "If N=0, an exception should be thrown.");

        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionJUnit.calculateAverage(-1);
        }, "If N=-1, an exception should be thrown.");
    }
    
    // Check that the average value in the range from 1 to 100
    @Test
    void testResultIsWithinExpectedRange() {
        int testN = 50; 
        double result = ExpressionJUnit.calculateAverage(testN);
        
        // The average value of N numbers from 1 to 100 must be in the range [1.0, 100.0]
        assertTrue(result >= 1.0, "The average value should be >= 1.0");
        assertTrue(result <= 100.0, "The average value should be <= 100.0");
    }
}
