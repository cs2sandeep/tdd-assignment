package org.example;

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
        String delimiter = "";
        if (numbers.startsWith("//")) {
            String[] firstLineAndRest = numbers.split("\n", 2);
            String firstLine = firstLineAndRest[0];
            numbers = firstLineAndRest[1]; // Process numbers as usual, now having removed optional delimiter

            delimiter = firstLine.substring(2); // After removing first two slashes
            if (delimiter.startsWith("[")) {
                delimiter = delimiter.replace("[", "");
                delimiter = delimiter.replace("]", "");
            }

        }

        int sum = 0;

        // When delimiter separated multiple numbers
        String splitPattern = "[\\n,]";
        if (delimiter.length() > 0) {
            numbers = numbers.replace(delimiter, ",");
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
