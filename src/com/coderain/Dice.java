package com.coderain;
import java.security.SecureRandom;

public class Dice {

    // Random
    private SecureRandom random = new SecureRandom();

    // I/O
    private Console console = new Console();

    // Variable to store result of roll; <-- Always gets reassigned zero each time roll() is called.

    // Value of each roll


    // @roll() Only responsible for rolling dice and is expecting two arguments
        // --> Other methods will handle parsing the ints for each argument nOfDice & diceType
        // --> The integer that will be returned is going to be used in the Calculator class for
    public int[] roll (int nOfDice, int diceType) {
            byte rollValue = 0;
            // Init
            console.log("Rolling D" + diceType + " " + nOfDice + "times... ");

            // 0: Total 1: Critical Hits
            int[] result = {0, 0};
            int count = 1;

            // Runs until number of dice are gone
            while (true) {
                if(nOfDice == 0) {
                    // Print out the the result of each of the dice rolled.
                    console.log("Total Damage: " + result[0]);
                    return result;
                }

                // Set value of each roll
                rollValue = (byte)(random.nextInt(diceType) + 1);
                // Print out the the result of each of the dice rolled.
                console.log("Die " + count++ + ": " + rollValue);

                if (diceType == 20 && rollValue == 20) {
                    console.logf("( Critical Hit! )");
                    result[1] += 1;
                }

                if (rollValue == 1) {
                    console.log("Critical Miss!");
                    rollValue = 0;
                }
                // Generate a random number every time within range of D4 - D20.
                result[0] += rollValue;

                nOfDice -= 1;
            }

    }

}
