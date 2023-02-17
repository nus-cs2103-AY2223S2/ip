package duke.ui;

import java.util.Scanner;

/**
 * The Ui class provides the user interface for the system.
 * It implements methods for printing messages and lists, as well as reading user input.
 *
 * @author owen-yap
 */
public class Ui {
    private final Scanner sc;
    private final String SEPARATOR_TOP = "________________________________________________\n";
    private final String SEPARATOR_BOT = "\n________________________________________________";
    /**
     * Constructs a new UI by initializing a Scanner object for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    /**
     * Prints an array of messages, surrounded by separators.
     *
     * @param msgs The message(s) to be printed
     */
    public String formatMsg(String[] msgs) {
        String res = "";
        for (String msg : msgs) {
            res = res + msg + "\n";
        }
        return SEPARATOR_TOP + res + SEPARATOR_BOT;
    }
    /**
     * Prints a message, surrounded by separators.
     *
     * @param msg The message to be printed
     */
    public String formatMsg(String msg) {
        return SEPARATOR_TOP + msg + SEPARATOR_BOT;
    }
    /**
     * Prints an array of items as a numbered list, surrounded by separators.
     *
     * @param list The array of items to be printed
     */
    public String listToString(String[] list) {
        String res = "";
        for (int i = 0; i < list.length; i++) {
            res += String.format("%d. %s\n", i + 1, list[i]);
        }
        return SEPARATOR_TOP + res + SEPARATOR_BOT;
    }

    /**
     * Prints a formatted list of strings to the console.
     *
     * @param msg The message to be printed before the list.
     * @param list The array of strings to be printed as a list.
     */
    public String listToString(String msg, String[] list) {
        String res = msg + "\n";
        for (int i = 0; i < list.length; i++) {
            res += String.format("%d. %s\n", i + 1, list[i]);
        }
        return SEPARATOR_TOP + res + SEPARATOR_BOT;
    }
}
