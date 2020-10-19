package com.coderain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // Will explain in the methods the use of.
    private String regex = "";
    private Pattern pattern;
    private Matcher matcher;

    // This validates a string from input and checks if the number entered is from 1 to n choices which represents the numbered menu.
    public int menuChoice(String sToValidate, int nOfChoices) {
        // This regex string represents any digit 0-9 that occurs once or twice so 0-99
        regex = "^\\d{1,2}$";

        // This compiles the regex and returns a class with methods to check for matches
        pattern = Pattern.compile(regex);

        // remove possible whitespace
        sToValidate = sToValidate.strip().trim();

        // The Matcher class will be assigned the result from the matcher method from the Pattern class
        matcher = pattern.matcher(sToValidate);

        // Set the initial value of result to -1 to return if no match found
        int result = -1;

        // matcher of course returns a boolean if there was a match found resulting from the Pattern.matcher() method.
        if (matcher.find()) {
            // Parse the string to integer
            int temp = Integer.parseInt(sToValidate);

            // check if the the integer surpasses the number of choices from the menu.
            if (temp <= nOfChoices) {
                // if true then number is okay to be returned and used to index an array.
                result = temp;
            }
        }
        return result;
    }

    // Like above but only takes one argument and that is the string to validate then parse.
    public int[] diceChoice(String sToValidate) {
        // This regex represents beginning to end of string the first char must be 1-6 and the next two chars must be d4 ... d20.
        regex = "^[1-6]d4|d6|d8|d10|d12|d20$";

        // Same process as above
        pattern = Pattern.compile(regex);
        sToValidate = sToValidate.strip().trim();
        matcher = pattern.matcher(sToValidate);

        // Only difference is this array which index 0 represents number of dice and index 1 is dice type.
//        int[] result = {<NUMBER-OF-DICE>, <DICE-TYPE>}
        int[] result = {-1, -1};

        if (matcher.find()) {

            // Spit the string and since there is guaranteed validation I can assign the values to the result
            // then result will be returned as an expected array;
            String[] parsed = sToValidate.split("d");
            result[0] = Integer.parseInt(parsed[0]);
            result[1] = Integer.parseInt((parsed[1]));
        }
        return result;
    }

}
