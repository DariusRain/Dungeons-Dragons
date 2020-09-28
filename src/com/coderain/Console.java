package com.coderain;

import java.util.Scanner;

// In charge of inputting & outputting to the user.
public class Console {

    // Every instantiated class will use this variable for input
    private Scanner input = new Scanner(System.in);

    // Every instantiated class will use this variable for output
        // --> output will keep concatenating previous elements with hyphens
    public String output;

    // Clears the output
    public void clear () {
        output = "";
    }
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void out (String s) {
        output += s;
    }

    public void log (String s) {
        System.out.println(s);
        output += s + "-";
    }

    public void logf (String s) {
        System.out.print(s);
        output += s + "-";
    }

    public String input (String message) {
        System.out.print(message);
        return input.nextLine();
    }

    public String input () {
        return input.nextLine();
    }

}
