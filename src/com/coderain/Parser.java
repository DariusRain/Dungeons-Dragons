package com.coderain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // This validates a string from input and checks if the number entered is from 1 to n choices.
    public int menuChoice(String sToValidate, int nOfChoices) {

        String regex = "^\\d{1,2}$";
        Pattern pattern = Pattern.compile(regex);
        sToValidate = sToValidate.strip().trim();
        Matcher matcher = pattern.matcher(sToValidate);
        int result = -1;
        if (matcher.find()) {
            int temp = Integer.parseInt(sToValidate);
            if (temp <= nOfChoices) {
                result = temp;
            }
        }
        return result;
    }

}
