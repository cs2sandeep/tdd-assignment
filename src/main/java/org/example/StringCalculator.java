package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {
        // When empty string given
        if (numbers.equals("")) {
            return 0;
        }

        // When single number
        boolean isOnlyDigits = numbers.matches(("\\d+"));
        if (isOnlyDigits) {
            return Integer.parseInt(numbers);
        }

        // If optional delimiter provided
        // extract out the first line from the given argument
        ArrayList<String> delimiters = new ArrayList<>();
        if (numbers.startsWith("//")) {
            String[] firstLineAndRest = numbers.split("\\n", 2);
            String firstLine = firstLineAndRest[0];
            numbers = firstLineAndRest[1]; // Process numbers as usual, now having removed optional delimiter

            String firstLineWithoutSlashes = firstLine.substring(
                2); // After removing first two slashes

            if (firstLineWithoutSlashes.contains("][")) { // Multiple types of delimiters, each enclosed in square brackets e.g. [*][%]
                firstLineWithoutSlashes = firstLineWithoutSlashes.substring(1, firstLineWithoutSlashes.length() - 1); // remove brackets from either end
                delimiters.addAll(Arrays.asList(firstLineWithoutSlashes.split("\\]\\[")));
            } else { // Only single kind of delimiter
                delimiters.add(firstLineWithoutSlashes); // either just the character like *
                if (delimiters.get(0).startsWith("[")) { // or enclosed in [***] for multiple
                    delimiters.set(0, delimiters.get(0).replace("[", ""));
                    delimiters.set(0, delimiters.get(0).replace("]", ""));
                }
            }

        }

        int sum = 0;

        // When delimiter separated multiple numbers
        String splitPattern = "[\\n,]";
        if (delimiters.size() > 0) {
            for (String delimiter : delimiters) {
                numbers = numbers.replace(delimiter, ",");
            }
        }
        String[] individualNums = numbers.split(splitPattern);

        List<Integer> numsInIntegerFormat = Arrays.stream(individualNums).map(numInStringFormat -> Integer.parseInt(numInStringFormat)).toList();


        // Negative number check
        validateForNegativesReturnNegativesAsStringAndThrow(numsInIntegerFormat);

        for (String individualNum : individualNums) {
            int iN = Integer.parseInt(individualNum);
            if (iN > 1000) {    // Ignore number greater than 1000 in the sum
                continue;
            }
            sum += iN;
        }

        return sum;
    }

    private static void validateForNegativesReturnNegativesAsStringAndThrow(List<Integer> numsInIntegerFormat) {
        List<Integer> negativeNumbers = numsInIntegerFormat.stream().filter(num -> num < 0).toList();
        if (negativeNumbers.size() > 0) {
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbers.stream().map(String::valueOf).collect(
                Collectors.joining(",")) );
        }
    }


}
