package com.coderain;
import java.security.SecureRandom;

public class Dice {

    // Random
    private SecureRandom random = new SecureRandom();

    // I/O
    private Console console = new Console();

    // Variable to store result of roll; <-- Always gets reassigned zero each time roll() is called.
    private int result;

    // Value of each roll
    private byte rollValue;

    // @roll() Only responsible for rolling dice and is expecting two arguments
        // --> Other methods will handle parsing the ints for each argument nOfDice & diceType
        // --> The integer that will be returned is going to be used in the Calculator class for
    public int roll (int nOfDice, int diceType) {
            // Init
            console.log("Rolling D" + diceType + " " + nOfDice + "times...");
            result = 0;
            rollValue = 0;
            int count = 1;

            // Runs until number of dice are gone
            while (true) {
                if(nOfDice == 0) {
                    // Print out the the result of each of the dice rolled.
                    return result;
                }

                // Set value of each roll
                rollValue = (byte)(random.nextInt(diceType) + 1);
                // Print out the the result of each of the dice rolled.
                console.log("Die " + count++ + ": " + rollValue);
                // Generate a random number every time within range of D4 - D20.
                result += rollValue;

                nOfDice -= 1;
            }

    }

}
