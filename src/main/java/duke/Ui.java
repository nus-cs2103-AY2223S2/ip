package duke;

import java.util.Scanner;

/** Handles input/output for Duke. */
public class Ui {

    private Scanner scanner;
    private StringBuilder msg;
    private boolean doPromptUserInput;

    // commonly-displayed elements
    public static final String DIVIDER = "____________________________________________________________";
    public static final String NEWL = "\n";
    public static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Initializes a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.msg = new StringBuilder();
        this.doPromptUserInput = true;
    }

    public void showWelcome() {
        this.addToMessage("Hello from");
        this.addToMessage(LOGO);
        this.addToMessage("Hello! I'm Duke.");
        this.addToMessage("What can I do for you?");
        this.displayMessage();
    }

    /**
     * Prints the stored message to the user.
     */
    public void displayMessage() {
        Ui.prettyPrint(this.msg.toString());
        this.clearMessage();
        if (this.doPromptUserInput) {
            System.out.print("> ");
        } else {
            this.doPromptUserInput = true;
        }
    }

    /** Clears the stored message. */
    public void clearMessage() {
        this.msg.setLength(0);
    }

    /**
     * Appends a string to the stored message.
     * 
     * @param toAdd The string to be appended to the stored message.
     */
    public void addToMessage(String toAdd) {
        this.msg.append(toAdd);
        this.msg.append(NEWL);
    }

    /**
     * Appends a string to the stored message.
     * 
     * @param toAdd             The string to be appended to the stored message.
     * @param doPromptUserInput Indicates whether the user should be prompted for
     *                          input after this message.
     */
    public void addToMessage(String toAdd, boolean doPromptUserInput) {
        this.doPromptUserInput = doPromptUserInput;
        this.addToMessage(toAdd);
    }

    /** Reads a line of input from the user. */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Pretty prints a given string.
     * 
     * @param text The string to be pretty-printed.
     */
    public static void prettyPrint(String text) {
        System.out.println(DIVIDER);
        System.out.print(text);
        System.out.println(DIVIDER);
        System.out.println();
    }
}
