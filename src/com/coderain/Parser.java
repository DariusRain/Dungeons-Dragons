package com.coderain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String regex = "";
    private Pattern pattern;
    private Matcher matcher;

    // This validates a string from input and checks if the number entered is from 1 to n choices.
    public int menuChoice(String sToValidate, int nOfChoices) {
        regex = "^\\d{1,2}$";
        pattern = Pattern.compile(regex);
        sToValidate = sToValidate.strip().trim();
        matcher = pattern.matcher(sToValidate);
        int result = -1;
        if (matcher.find()) {
            int temp = Integer.parseInt(sToValidate);
            if (temp <= nOfChoices) {
                result = temp;
            }
        }
        return result;
    }

    public int[] diceChoice(String sToValidate) {
        regex = "^[1-6]d4|d6|d8|d10|d12|d20$";
        pattern = Pattern.compile(regex);
        sToValidate = sToValidate.strip().trim();
        matcher = pattern.matcher(sToValidate);
        int[] result = {-1, -1};
        if (matcher.find()) {
            String[] parsed = sToValidate.split("d");
            result[0] = Integer.parseInt(parsed[0]);
            result[1] = Integer.parseInt((parsed[1]));
        }
        return result;
    }

}
