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
        sc = new Scanner(System.in);
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
     * Formats the parameter {@code numTasks} into a string.
     *
     * @param numTasks The number of tasks in the task list.
     * @return A string indicating the number of tasks in the task list.
     */
    public String formatNumberOfTasksAsString(int numTasks) {
        assert numTasks >= 0;

        if (numTasks == 1) {
            return "There is 1 task in the list";
        } else {
            return "There are " + numTasks + " tasks in the list";
        }
    }

    /**
     * Reads the next line of user input.
     *
     * @return A string representing a line of user input, excluding the newline character.
     */
    public String readLine() {
        return sc.nextLine();
    }
}
