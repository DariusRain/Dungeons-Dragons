package com.coderain;

import java.util.ArrayList;
import static java.lang.System.exit;

public class Game {
    private static final String[] characterTypes = {
            "BARBARIAN", "BARD", "CLERIC",
            "DRUID", "FIGHTER", "MONK",
            "PALADIN", "RANGER", "ROUGE",
            "SORCERER", "WARLOCK", "WIZARD"
    };

    private static final int[] diceTypes = {4, 6, 8, 10, 12, 20};

    private final Calculator calculator = new Calculator();
    private final Console console = new Console();
    private final Parser parser = new Parser();

    private final ArrayList<Character> characters = new ArrayList<>();
    private int onPlayer = 0;
    public boolean nextMenu = false;
    private int nOfReRolls = 0;

    public void start() {
        while (!nextMenu) {
            displayMainMenu();
        }

        while (nextMenu) {
            displayAttackMenu();
        }
        if (gameOver) {
            console.clearScreen();
            console.log("The winner of the game is " + characters.get(0).getPlayer());
            exit(0);
        }
    }

    public void next() {
        if (onPlayer <= characters.size() - 1) {
            onPlayer += 1;
        } else {
            onPlayer = 0;
        }
    }

    public void displayDice() {
        int counter = 0;
        for (int die: diceTypes) {
            if (counter % 4 == 0) {
                console.log("\n");
            }
            counter += 1;
            console.logf(counter + ": D" + die + "  ");
        }
        console.log("\n");
    }
    public void displayAttackMenu() {
        console.log("\n\n\n");
        console.log("Dungeons & Dragons (Attack Menu):");
        displayStatus();
        Character attacker = characters.get(onPlayer <= characters.size() - 1 ? onPlayer : 0);
        int attackChoice = askForMenuChoice("Choose who to attack ( " + attacker.getPlayer() + " / " + attacker.getType() + " / " + attacker.getHealth() +" ):" + (0 < nOfReRolls? "(Re-roll #" + nOfReRolls + ") " : " "), characters.size() + 1);
        displayDice();
        int diceChoice = askForMenuChoice("What type of dice do you want to roll: ", diceTypes.length);
        int qtyOfDice = askForMenuChoice("How many dice do you want to roll 1 - 6: ", 6);
        console.log("chose: " + (attackChoice - 1) + " On Player: " + onPlayer);
        int[] results = calculator.attack(qtyOfDice, diceTypes[diceChoice - 1], characters.get(attackChoice - 1));
        nOfReRolls += results[1];
        if (0 < nOfReRolls) {
            console.log(attacker.getPlayer() + " " + "has "  + nOfReRolls + " re-roll(s)...\n");
            nOfReRolls -= 1;
            displayAttackMenu();
        } else {
            next();
        }
    }

    public void askForPlayerName(int choice) {
        String playerName = console.input("What is your name: ").strip().trim();
        int nOfDupes = 0;

        for(Character character: characters) {
            if(character.getPlayer().equals(playerName)) {
                nOfDupes += 1;
            }
        }
        if (nOfDupes > 0) {
            characters.add(new Character(characterTypes[choice - 1], playerName + nOfDupes) );
        } else {
            characters.add(new Character(characterTypes[choice - 1], playerName));
        }
    }

    public int askForMenuChoice(String message, int counter) {

        int choice = -1;

        while (choice == -1) {
            choice = parser.menuChoice(console.input(message).trim().strip(), counter);
        }

        if (choice == counter + 1) {
            this.nextMenu = true;
            console.log("\n");
            console.log("Proceeding...");
        }


        console.log("\n");
        return choice;
    }

    public void displayStatus() {
        int counter = 0;
        for (Character character : characters) {
            counter += 1;
            console.log(counter + ": " + character.getPlayer() + " ( " + character.getType() + " / AC:" + character.getHealth() + " )");
        }
    }

    public void displayMainMenu() {
        int counter = 0;
        console.log("\nDungeons & Dragons (Main Menu)");
        console.log("==============================\n");
        for (String character : characterTypes) {
            counter += 1;
            if (counter % 5 == 0) {
                console.log("\n");
            }
            console.logf(counter + ": " + character + "  ");
        }

        console.log("\n");
        console.log((counter + 1) + " Next");
        console.log("\nPlayers:");
        displayStatus();
        console.log("\n");
        int choice = askForMenuChoice("Please choose a character type: ", counter + 1);
        if (choice < counter + 1) {
            askForPlayerName(choice);
        } else {
            nextMenu = true;
        }
        console.clearScreen();
    }
    public boolean isGameOver() {
        int survivorCount = 0;
        int counter = 0;
        for (Character character: characters) {
            if (character.getHealth() <= 0) {
                console.log(character.getPlayer() + " has been eliminated...");
                characters.remove(counter);
                continue;
            } else {
                survivorCount += 1;
            }
            counter += 1;
        }
        if (survivorCount == 1) {
            return true;
        }
    }

}

