package duke;

import duke.Commands.Command;

/**
 * This class handles user interactions/messages
 */
public class Ui {
    private static final String WELCOME = "Hello! Welcome to Duke. Let's start task tracking!";

    private static final String GOODBYE = "Auf Wiedersehen!";

    public Ui() {
        // empty
    }

    /**
     * Prints the current list of tasks
     *
     * @param tasks The current list of tasks to be printed
     */
    public void printList(TaskList tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        System.out.println(res);
    }

    /**
     * Prints a welcome message
     */
    public void printWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Prints a farewell message
     */
    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints a descriptive message of a command
     * @param command The command to be described
     */
    public void printCommandMessage(Command command) {
        System.out.println(command);
    }
}
