package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {
        String numbersOriginal = numbers;

        // When empty string given
        if (numbers.equals("")) {
            return 0;
        }

        // When single number
        boolean isOnlyDigits = numbers.matches(("\\d+"));
        if (isOnlyDigits) {
            return Integer.parseInt(numbers);
        }

        // If optional delimiter(s) provided
        // extract out the delimiter(s) and remove the delimiter line from the given string
        List<String> delimiters = extractDelimiters(numbers);
        if (numbers.startsWith("//")) {
            numbers = numbers.split("\\n", 2)[1];
        }

        // Flag for even-indices sum or odd-indices sum
        // Remove delimiters of 0 or 1, as they are not intended as flags not delimiters
        String sumFlag = "";
        if (delimiters.size() > 0 && delimiters.get(0).equals("0")) {
            sumFlag = "0";
            delimiters.remove(0);
        } else if (delimiters.size() > 0 && delimiters.get(0).equals("1")) {
            sumFlag = "1";
            delimiters.remove(0);
        }

        // When delimiter separated multiple numbers, swap each delimiter with comma
        String splitPattern = "[\\n,]";
        if (delimiters.size() > 0) {
            for (String delimiter : delimiters) {
                numbers = numbers.replace(delimiter, ",");
            }
        }
        String[] individualNums = numbers.split(splitPattern);
        List<Integer> numsInIntegerFormat = Arrays.stream(individualNums).map(Integer::valueOf)
            .collect(Collectors.toList());

        // Negative number check
        validateForNegativesReturnNegativesAsStringAndThrow(numsInIntegerFormat);

        // Sum
        int index = 0;
        int increment = 1;

        if (sumFlag == "0") {
            index = 0;
            increment = 2;
        } else if (sumFlag == "1") {
            index = 1;
            increment = 2;
        }

        int sum = 0;
        for (; index < numsInIntegerFormat.size(); index += increment) {
            if (numsInIntegerFormat.get(index) > 1000) {
                continue;
            }
            sum += numsInIntegerFormat.get(index);
        }
        return sum;
    }

    private static void validateForNegativesReturnNegativesAsStringAndThrow(
        List<Integer> numsInIntegerFormat) {
        List<Integer> negativeNumbers = numsInIntegerFormat.stream().filter(num -> num < 0)
            .toList();
        if (negativeNumbers.size() > 0) {
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbers.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    private List<String> extractDelimiters(String fullDelimiterString) {
        List<String> extractedDelimiters = new ArrayList<>();

        if (fullDelimiterString.startsWith("//")) {
            String[] firstLineAndRest = fullDelimiterString.split("\\n", 2);
            String firstLine = firstLineAndRest[0];

            String firstLineWithoutSlashes = firstLine.substring(
                2); // After removing first two slashes

            if (firstLineWithoutSlashes.contains(
                "][")) { // Multiple types of delimiters, each enclosed in square brackets e.g. [*][%]
                firstLineWithoutSlashes = firstLineWithoutSlashes.substring(1,
                    firstLineWithoutSlashes.length() - 1); // remove brackets from either end
                extractedDelimiters.addAll(Arrays.asList(firstLineWithoutSlashes.split("\\]\\[")));
            } else { // Only single kind of delimiter
                extractedDelimiters.add(
                    firstLineWithoutSlashes); // either just the character like *
                if (extractedDelimiters.get(0)
                    .startsWith("[")) { // or enclosed in [***] for multiple
                    extractedDelimiters.set(0, extractedDelimiters.get(0).replace("[", ""));
                    extractedDelimiters.set(0, extractedDelimiters.get(0).replace("]", ""));
                }
            }

        }

        return extractedDelimiters;
    }


}
