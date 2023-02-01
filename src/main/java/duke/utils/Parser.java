package duke.utils;

import java.util.Scanner;

/**
 * Interpreter class to format user input into tokens
 */
public class Parser {
    private static Scanner sc = new Scanner(System.in);
    private String[] tokens;

    /**
     * Formats user input from standard input to String array
     * @return String array of tokens
     */
    public String[] tokenise() {
        this.tokens = Parser.sc.nextLine().split(" ");
        return this.tokens;
    }
    
}
