package duke.utilities;

import java.util.Scanner;

/**
 * The ui manager for Duke.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Instantiates a new ui manager.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================\n";
        System.out.print(welcomeMessage);
    }

    /**
     * Prints the user prompt.
     */
    public void showPrompt() {
        System.out.print("\n>> ");
    }

    /**
     * Prints the specified message.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Formats and prints the number of tasks.
     *
     * @param numTasks The number of tasks in the task list.
     */
    public void showNumberOfTasks(int numTasks) {
        if (numTasks == 1) {
            System.out.println("There is 1 task in the list");
        } else {
            System.out.println("There are " + numTasks + " tasks in the list");
        }
    }

    /**
     * Reads the next line of user input.
     *
     * @return A string representing a line of user input, excluding the newline character.
     */
    public String readLine() {
        return this.sc.nextLine();
    }
}
