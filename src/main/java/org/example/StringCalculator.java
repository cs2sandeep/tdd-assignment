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
        String firstLine = "";
        if (numbers.startsWith("//")) {
            String[] firstLineAndRest = numbers.split("\n", 2);
            firstLine = firstLineAndRest[0];
            delimiter = firstLine.substring(2); // After removing first two slashes
            numbers = firstLineAndRest[1]; // Process numbers as usual, now having removed optional delimiter

        }

        int sum = 0;

        // When delimiter separated multiple numbers
        String splitPattern = "[\\n,]";
        if (delimiter.length() > 0) {
            System.out.println("Delimiter: " + delimiter);
            splitPattern =
                "[\\n" + delimiter + "]"; // If delimiter provided, it is now changed from ','
        }
        String[] individualNums = numbers.split(splitPattern);

        // Flag for negative number
        boolean isNegativePresent = false;
        String negativeNumbers = "";

        for (String individualNum : individualNums) {
            int iN = Integer.parseInt(individualNum);
            if(iN < 0) {
                isNegativePresent = true;
                negativeNumbers += negativeNumbers.length() > 0 ? "," + individualNum : individualNum;
            } else if(!isNegativePresent) { // Once any negative number is found, no summation
                sum += iN;
            }
        }
        if(isNegativePresent) {
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbers);
        }
        return sum;
    }

}
