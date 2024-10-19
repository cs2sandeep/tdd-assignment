package org.example;

public class StringCalculator {

    public int add(String numbers) {
        // When a single number is present
        boolean isOnlyDigits = numbers.matches(("\\d+"));
        if (isOnlyDigits) {
            return Integer.parseInt(numbers);
        }

        return 0;
    }

}
