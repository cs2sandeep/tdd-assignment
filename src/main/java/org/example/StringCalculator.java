package org.example;

public class StringCalculator {

    public int add(String numbers) {
        // When empty string given
        if(numbers.equals("")) {
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
        if(numbers.startsWith("//")) {
            String[] firstLineAndRest = numbers.split("\n", 2);
            firstLine = firstLineAndRest[0];
            delimiter = firstLine.substring(2); // After removing first two slashes
            numbers = firstLineAndRest[1]; // Process numbers as usual, now having removed optional delimiter

        }


        int sum = 0;

        // When comma separated two numbers
        String splitPattern = "[\\n,]";
        if (delimiter.length() > 0) {
            splitPattern = "[\\n" + delimiter + "]"; // If delimiter provided, it is now changed from ','
        }
        String[] individualNums = numbers.split(splitPattern);
        for(String individualNum : individualNums) {
            sum += Integer.parseInt(individualNum);
        }
        return sum;
    }

}
