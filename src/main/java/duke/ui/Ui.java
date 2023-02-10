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
    public void printMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println(msg);
        }
        printSeparator();
    }
    /**
     * Prints a message, surrounded by separators.
     *
     * @param msg The message to be printed
     */
    public void printMsg(String msg) {
        printSeparator();
        System.out.println(msg);
        printSeparator();
    }
    /**
     * Prints an array of items as a numbered list, surrounded by separators.
     *
     * @param list The array of items to be printed
     */
    public void printList(String[] list) {
        printSeparator();
        for (int i = 0; i < list.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
        printSeparator();
    }

    /**
     * Prints a formatted list of strings to the console.
     *
     * @param msg The message to be printed before the list.
     * @param list The array of strings to be printed as a list.
     */
    public void printList(String msg, String[] list) {
        printSeparator();
        System.out.println(msg);
        for (int i = 0; i < list.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
        printSeparator();
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcomeMsg() {
        String[] welcomeMsg = {"Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);
    }
    /**
     * Reads a line of input from the user.
     *
     * @return The input entered by the user
     */
    public String readInput() {
        return this.sc.nextLine();
    }
    /**
     * Prints a separator.
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
