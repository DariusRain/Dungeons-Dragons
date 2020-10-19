package com.coderain;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.System.exit;

public class Game {

    // These are all the character types a user can choose from. ( Had them as enums at first)
    private static final String[] characterTypes = {
            "BARBARIAN", "BARD", "CLERIC",
            "DRUID", "FIGHTER", "MONK",
            "PALADIN", "RANGER", "ROUGE",
            "SORCERER", "WARLOCK", "WIZARD"
    };

    // Calculator is responsible for calculating an attack.
    private final Calculator calculator = new Calculator();

    // Console is responsible for only I/O operations.
    private final Console console = new Console();

    // Parser is responsible for parsing user input for menu & attack.
    private final Parser parser = new Parser();

    // This will hold all the players throughout the game.
    private final ArrayList<Character> characters = new ArrayList<>();

    // Keeps track of what player's turn it is.
    private int onPlayer = 0;

    // This boolean is set as false until the attack menu has been selecting thus starting the game.
    public boolean nextMenu = false;

    // Keeps track if a player has re-rolls.
    private int nOfReRolls = 0;


    // This is the function that starts the entire game and displays the menus to the console
    public void start() {

        // While the attack menu has not been selected and players are still being added.
        while (!nextMenu) {

            // Show the main menu for a new player to join the player array.
            mainMenu();
        }

        // Since nextMenu is now true, the attack menu has been selected and the game has started.
        while (nextMenu) {
            // Show the attack menu and apply an attack to specific player.
            attackMenu();

            // Of course the isGameOver method returns a boolean indicating there is only one player left standing.
            if (isGameOver()) {

                // Executing the below to notify that the game is over and exit the program.
                console.clearScreen();
                console.log("The winner of the game is " + characters.get(0).getPlayer());
                exit(0);
            }
        }
    }

    // This method executes after a players turn has ended
    public void next() {

        // Make sure the player is within range
        if (onPlayer <= characters.size() - 1) {
            onPlayer += 1;
        } else {
            // Else set it back to the first in line or first index of array.
            onPlayer = 0;
        }

    }


    // Displays and calls a function that takes input for attack
    public void attackMenu() {
        console.log("\n\n\n");
        console.log("Dungeons & Dragons (Attack Menu):");

        // Since there is access to what player's turn it is we will use that to get the index in the characters array.
        Character attacker = characters.get(onPlayer <= characters.size() - 1 ? onPlayer : 0);

        // Call the getAttackChoice method to get the int of the index of who will be the defender in the characters array.
        int attackChoice = getAttackChoice(attacker);

        // Call get attackDice to get the array[0]=n of dice array[1] type of dice
        int [] diceChoices = getAttackDice();

        // Display to the user what he chose.

        console.log("chose: " + (attackChoice - 1) + " On Player: " + onPlayer);

        // User the Calculator class and use the attack method to get the results of the attack.
        int[] results = calculator.attack(diceChoices[0], diceChoices[1], characters.get(attackChoice - 1));

        // If results[1] representing re-rolls has a value then it will be incremented & accounted for below.
        nOfReRolls += results[1];

        // If there is re-rolls recursively call this function again.
        if (0 < nOfReRolls) {

            // Display to the attacker that they got re-roll(s).
            console.log(attacker.getPlayer() + " " + "has "  + nOfReRolls + " re-roll(s)...\n");

            nOfReRolls -= 1;

            attackMenu();
        } else {
            next();
        }
    }

    public int getAttackChoice(Character attacker) {
        displayStatus();
        int attackChoice = askForMenuChoice("Choose who to attack ( " + attacker.getPlayer() + " / " + attacker.getType() + " / " + attacker.getHealth() +" ):" + (0 < nOfReRolls? "(Re-roll #" + nOfReRolls + ") " : " "), characters.size() + 1);
        return attackChoice;
    }

    public int[] getAttackDice() {
        int[] diceChoices = parser.diceChoice(console.input("Choose dice in format '<1-6>d<4,6,8,10,12,20>' '6d20': "));
            if (diceChoices[0] > -1 && diceChoices[1] > -1) {
                return  diceChoices;
            } else {
                console.log("( Invalid dice choice! )");
                return getAttackDice();
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

    public void mainMenu() {
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

        ArrayList remove = new ArrayList<>();

        Iterator<Character> characterArr = characters.iterator();

        while (characterArr.hasNext()) {
            Character character = characterArr.next();
            if (character.getHealth() <= 0) {
                console.log(character.getPlayer() + " has been eliminated...");
                remove.add(counter);
                continue;
            } else {
                survivorCount += 1;
            }
            counter += 1;
        }
        Iterator removeArr = remove.iterator();
        while (removeArr.hasNext()) {
            characters.remove(removeArr.next());
        }

        return survivorCount == 1;
    }

}

