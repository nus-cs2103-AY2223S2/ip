package duke;

import java.util.Scanner;

/**
 * Functions related to interacting with the user.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /** For checking whether to continue running Duke, i.e. exit command has not been called. */
    private static boolean isRunning = true;
    /** Command line input from user stored here. */
    private static String line;

    public static boolean getIsRunning() {
        return isRunning;
    }

    public static String getLine() {
        return line;
    }

    /**
     * Crafts string output for showing number of tasks in a list.
     * @param numTask Number of tasks in the list
     * @return Crafted message
     */
    public static String numTaskToString(int numTask) {
        return String.format("Now you have %d task%s in the list", numTask, numTask == 1 ? "" : "s");
    }

    // Greets user with welcome message, and gets the first command.
    public static void greetUser() {
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        try {
            if (isRunning) {
                line = sc.nextLine();
            }
        } catch (java.util.NoSuchElementException e) {
            isRunning = false;
        }
    }

    // Scan for next command from user.
    public static String getNextCommand() {
        System.out.println("");
        try {
            if (isRunning) {
                line = sc.nextLine();
            }
        } catch (java.util.NoSuchElementException e) {
            isRunning = false;
        }
        return line;
    }

    /**
     * Outputs to console a message.
     * 
     * @param msg Message to display
     */
    public static void displayMsg(String msg) {
        System.out.println(indentString(wrapMessageBorder(msg), 1));
    }

    /**
     * Wraps a message with top & bottom line borders.
     * 
     * @param msg Message to enclose in borders
     * @return Bordered message
     */
    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }

    /**
     * Indents a message by a specified indentation level.
     * 
     * @param msg Message to indent
     * @param indendationLevel Number of indents
     * @return Indented message.
     */
    public static String indentString(String msg, int indendationLevel) {
        String indent = "  ".repeat(indendationLevel);
        String[] lines = msg.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(indent + lines[i] + (i + 1 < lines.length ? "\n" : ""));
        }
        return result.toString();
    }

    /**
     * Greets goodbye and shuts down Duke.
     */
    public static void shutDown() {
        isRunning = false;
    }
}
