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


    static Stream<Arguments> provideSingleNumberStrings() {
        return Stream.of(
            Arguments.of("1", 1),
            Arguments.of("2", 2),
            Arguments.of("0", 0),
            Arguments.of("100", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSingleNumberStrings")
    void add_stringSingleNumber_returnsThatNumber(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given single number String " + inputNumbersString + ", should return " + expected);
    }


    static Stream<Arguments> provideCommaSeparatedNumbersStrings() {
        return Stream.of(
            Arguments.of("1,5", 6),
            Arguments.of("1,5,1000", 1006)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCommaSeparatedNumbersStrings")
    void add_stringCommaSeparatedNumbers_returnsSum(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum, "when given comma separated numbers String " + inputNumbersString + ", should return " + expected);
    }


    static Stream<Arguments> provideNewlinesTooBetweenNumbersStrings() {
        return Stream.of(
            Arguments.of("1\n2,3", 6),
            Arguments.of("1,2\n3", 6),
            Arguments.of("1,2,3", 6)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNewlinesTooBetweenNumbersStrings")
    void add_stringNewlinesTooBetweenNumbers_returnsSum(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum, "when given newlines too between numbers String " + inputNumbersString + ", should return " + expected);
    }


    @Test
    void add_stringSlashSlashDelimiterNewline1delimiter2_returns3() {
        String inputString = "//;\n1;2";
        int expectedSum = 3;

        int actualSum = stringCalculator.add(inputString);

        assertEquals(expectedSum, actualSum, "when given string \"//;\\n1;2\", should return 3");
    }


}