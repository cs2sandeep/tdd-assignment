package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void init() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void add_emptyString_returns0() {
        String inputString = "";
        int expectedSum = 0;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given empty string, should return 0");
    }

}