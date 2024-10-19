package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    static Stream<Arguments> provideStringsForSum() {
        return Stream.of(
            Arguments.of("1", 1),
            Arguments.of("2", 2),
            Arguments.of("0", 0),
            Arguments.of("100", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForSum")
    void add_stringSingleNumber_returnsThatNumber(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given String " + inputNumbersString + ", should return " + expected);
    }

    @Test
    void add_string1comma5_returns6() {
        String inputString = "1,5";
        int expectedSum = 6;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"1,5\", should return 6");
    }

    @Test
    void add_string1comma5comma1000_returns1006() {
        String inputString = "1,5,1000";
        int expectedSum = 1006;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"1,5,1000\", should return 1006");
    }

    @Test
    void add_string1newline2comma3_returns6() {
        String inputString = "1\n2,3";
        int expectedSum = 6;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"1\n2,3\", should return 6");
    }

    @Test
    void add_string1newline2newline3_returns6() {
        String inputString = "1\n2\n3";
        int expectedSum = 6;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"1\n2\n3\", should return 6");
    }

    @Test
    void add_stringSlashSlashDelimiterNewline1delimiter2_returns3() {
        String inputString = "//;\n1;2";
        int expectedSum = 3;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"//;\\n1;2\", should return 3");
    }


}