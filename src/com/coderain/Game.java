package com.coderain;

import java.util.ArrayList;

public class Game {
    private Console console = new Console();
    private ArrayList<Character> characters = new ArrayList<>();

    public void menu(String type) {
        int counter = 0;
        switch (type) {
            case "Main":
                console.log("Dungeons & Dragons (Main Menu):");
                for (CharacterTypes character : CharacterTypes.values()) {
                    counter += 1;
                    if (counter % 5 == 0) {
                        console.log("\n");
                    }
                    console.logf(counter + " " + character.toString() + "  ");
                }
                console.log("\n");
                console.log((counter + 1) + " Done  " + (counter + 2) + " Exit");
                console.log("\n");

                console.input("Please choose a character:");
                counter = 0;
                break;

            default:
                console.log("Invalid menu type.");
                break;
        }
    }

    ;

    public void menu(String type, Character character) {
        int counter = 0;
        switch (type) {
            case "Attack":
                console.log("Dungeons & Dragons (Attack Menu):");
                for (Character c : characters) {
                    counter += 1;
                    console.log(counter + " " + c.getPlayer() + "( " + c.getType() + " / AC:" + c.getHealth() + " )");
                }
                break;
            default:
                console.log("Invalid menu type.");
                break;
        }
    }
}
//    };
    //    private void next();
    //    private void start();

