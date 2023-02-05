package duke;

import java.util.Scanner;

/**
 * Class for Parser object.
 * Parser parses the input from user and extracts information to perform the respective actions.
 * 
 * @author Bryan Tan
 */
public class Parser {
    private Scanner sc = new Scanner(System.in);
    private String input;
    private String[] raw;

    /**
     * Initialises the display output to prompt for user input.
     * Stores command under input variable.
     */
    public void initialise() {
        System.out.print("User: ");
        this.raw = sc.nextLine().split(" ");
        this.input = raw[0];
    }

    /**
     * Reads and parses the next input.
     */
    public void newInput() {
        this.raw = sc.nextLine().split(" ");
        this.input = raw[0];
    }

    /**
     * Checks which command the user has inputted.
     * 
     * @param s String description of recognised inputs.
     * @return returns true if input matches a recognised input.
     */
    public boolean isInput(String s) {
        return this.input.equalsIgnoreCase(s);
    }

    public String currInput() {
        return this.input;
    }

    public int getTaskNum() {
        return Integer.parseInt(raw[1]) - 1;
    }

    public String[] getRaw() {
        return this.raw;
    }

    /**
     * Creates String of keyword to be searched with.
     * 
     * @return Keyword or phrase.
     */
    public String findTaskParsed() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < this.raw.length; i++) {
            sb.append(this.raw[i] + " ");
        }
        return sb.toString();
    }

    /**
     * Closes parser.
     */
    public void closeParser() {
        sc.close();
    }
    
}
