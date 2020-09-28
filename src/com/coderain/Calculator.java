package com.coderain;

public class Calculator {
    Dice dice = new Dice();
    public int[] attack(int qtyOfDice, int diceChoice,  Character defender) {
        int[] attackResult = dice.roll(qtyOfDice, diceChoice);
        defender.setHealth(defender.getHealth() - attackResult[0]);
        return attackResult;
    }
}
