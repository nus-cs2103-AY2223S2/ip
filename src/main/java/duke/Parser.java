package duke;

import java.util.Scanner;

/**
 * This class handles the parsing of user input by breaking input strings into tokens.
 */
public class Parser {
    private final Scanner sc;

    /**
     * Standard constructor for an instance of Parser. This sets scanner input as <code>System.in</code>.
     */
    Parser() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line, and breaks the result into tokens.
     * @return A <code>String[]</code> containing strings from input, with each token delimited in the input
     * by a whitespace.
     */
    String[] parseUserInput() {
        String input = sc.nextLine();
        return input.split(" ");
    }
}
