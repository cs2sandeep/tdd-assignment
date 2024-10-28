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
            "when given single number String " + inputNumbersString + ", should return "
                + expected);
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
        assertEquals(expected, actualSum,
            "when given comma separated numbers String " + inputNumbersString + ", should return "
                + expected);
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
        assertEquals(expected, actualSum,
            "when given newlines too between numbers String " + inputNumbersString
                + ", should return " + expected);
    }


    static Stream<Arguments> provideCustomDelimitersBetweenNumbersStrings() {
        return Stream.of(
            Arguments.of("//;\n1;2", 3),
            Arguments.of("//^\n1^2", 3),
            Arguments.of("//%\n1%2%3", 6),
            Arguments.of("//%\n1%2\n3", 6) // Assuming newlines between numbers continue to be handled even though default delimiter(comma) has been replaced with custom delimiter

        );
    }

    @ParameterizedTest
    @MethodSource("provideCustomDelimitersBetweenNumbersStrings")
    void add_stringCustomDelimitersBetweenNumbers_returnsSum(String inputNumbersString,
        int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given custom delimiters between numbers String " + inputNumbersString
                + ", should return " + expected);
    }


    static Stream<Arguments> provideNegativeNumbersStrings() {
        return Stream.of(
            Arguments.of("1,2,-3", "negative numbers not allowed -3"),
            Arguments.of("1,-2,-3", "negative numbers not allowed -2,-3"),
            Arguments.of("-1,-2,-3", "negative numbers not allowed -1,-2,-3")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNegativeNumbersStrings")
    void add_stringNegativeNumbers_returnsSum(String inputNumbersString, String expectedMessage) {
        Exception thrown = assertThrows(Exception.class, () -> {
            stringCalculator.add(inputNumbersString);
        });

        assertEquals(expectedMessage, thrown.getMessage());
    }

    static Stream<Arguments> provideNumberGreaterThan1000Strings() {
        return Stream.of(
            Arguments.of("2,1001", 2),
            Arguments.of("1001\n1001", 0),
            Arguments.of("//(\n1001(3", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumberGreaterThan1000Strings")
    void add_stringNumberGreaterThan1000_returnsSum(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given number greater than 1000 String " + inputNumbersString + ", should return "
                + expected);
    }

    static Stream<Arguments> provideAnyLengthDelimiterStrings() {
        return Stream.of(
            Arguments.of("//[***]\n1***2***3", 6),
            Arguments.of("//[%%]\n0%%1%%2", 3),
            Arguments.of("//[(]\n1(2(4", 7)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnyLengthDelimiterStrings")
    void add_stringAnyLengthDelimiter_returnsSum(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given any length delimiter String " + inputNumbersString
                + ", should return " + expected);
    }

    static Stream<Arguments> provideMultipleDelimitersStrings() {
        return Stream.of(
            Arguments.of("//[*][%]\n1*2%3", 6),
            Arguments.of("//[^][#]\n0#0^3", 3),
            Arguments.of("//[^][#][&][%]\n1%2&3#4^5", 15)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMultipleDelimitersStrings")
    void add_stringMultipileDelimiters_returnsSum(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given multiple delimiters String " + inputNumbersString
                + ", should return " + expected);
    }

    static Stream<Arguments> provideMultipleDelimitersWithLengthLongerThanOneStrings() {
        return Stream.of(
            Arguments.of("//[***][%%]\n1***2%%3", 6),
            Arguments.of("//[^^^^][#]\n0#0^^^^3", 3),
            Arguments.of("//[^][##][&&&][%%%%]\n1%%%%2&&&3##4^5", 15)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMultipleDelimitersWithLengthLongerThanOneStrings")
    void add_stringMultipileDelimitersWithLengthLongerThanOne_returnsSum(String inputNumbersString,
        int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given multiple delimiters with length longer then one String "
                + inputNumbersString
                + ", should return " + expected);
    }

    static Stream<Arguments> getSumOfSkippedPlaces() {
        return Stream.of(
            Arguments.of("//0\n1,2,3,4,5", 9),
            Arguments.of("//1\n1,2,3,4,5", 6)
        );
    }

    @ParameterizedTest
    @MethodSource("getSumOfSkippedPlaces")
    void add_getSumOfEvenPlaces_returnsSumOfSkippedPlaces(String inputNumbersString, int expected) {
        int actualSum = stringCalculator.add(inputNumbersString);
        assertEquals(expected, actualSum,
            "when given 0 or 1 flag to sum skipped places "
                + inputNumbersString
                + ", should return " + expected);
    }



}