package org.example;

import java.util.ArrayList;
import java.util.Arrays;

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

            String firstLineWithoutSlashes = firstLine.substring(2); // After removing first two slashes

            if(firstLineWithoutSlashes.contains("][")) { // Multiple types of delimiters, each enclosed in square brackets e.g. [*][%]
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
            for(String delimiter: delimiters) {
                numbers = numbers.replace(delimiter, ",");
            }
        }
        String[] individualNums = numbers.split(splitPattern);

        // Flag for negative number
        boolean isNegativePresent = false;
        String negativeNumbers = "";

        for (String individualNum : individualNums) {
            int iN = Integer.parseInt(individualNum);
            if (iN > 1000) {    // Ignore number greater than 1000 in the sum
                continue;
            }

            if (iN < 0) {
                isNegativePresent = true;
                negativeNumbers +=
                    negativeNumbers.length() > 0 ? "," + individualNum : individualNum;
            } else if (!isNegativePresent) { // Once any negative number is found, no summation
                sum += iN;
            }
        }
        if (isNegativePresent) {
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbers);
        }
        return sum;
    }

}
