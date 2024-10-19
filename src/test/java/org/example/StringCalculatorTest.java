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

    @Test
    void add_string1_returns1() {
        String inputString = "1";
        int expectedSum = 1;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"1\", should return 1");
    }

    @Test
    void add_string2_returns2() {
        String inputString = "2";
        int expectedSum = 2;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"2\", should return 2");
    }

}