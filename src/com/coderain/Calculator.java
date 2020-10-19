package com.coderain;

public class Calculator {

    Dice dice = new Dice();

    // This method takes in the number of dice and dice choice. Then the defender of the attack.
    public int[] attack(int qtyOfDice, int diceChoice,  Character defender) {

        // dice.roll returns an array array[0] = total damage and array[1] is re-rolls if special hit.
        int[] attackResult = dice.roll(qtyOfDice, diceChoice);

        // Set the defender's health to its current health minus the attackResult[0] which is total damage.
        defender.setHealth(defender.getHealth() - attackResult[0]);

        // return the attackResult array in case re-rolls are passed to index 1.
        return attackResult;
    }
}
