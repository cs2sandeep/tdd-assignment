package org.example;

public class StringCalculator {

    public int add(String numbers) {
        // When empty string given
        if(numbers.equals("")) {
            return 0;
        }

        // When a single number is given
        boolean isOnlyDigits = numbers.matches(("\\d+"));
        if (isOnlyDigits) {
            return Integer.parseInt(numbers);
        }

        int sum = 0;

        // When comma separated two numbers are given
        String[] individualNums = numbers.split(",");
        for(String individualNum : individualNums) {
            sum += Integer.parseInt(individualNum);
        }
        return sum;
    }

}
