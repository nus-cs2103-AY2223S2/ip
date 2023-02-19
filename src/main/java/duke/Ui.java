package duke;

import duke.commands.Command;
import duke.dukeexception.DukeException;

/**
 * This class handles user interactions/messages.
 */
public class Ui {
    private static final String WELCOME = "Hello! Welcome to Duke. Let's start task tracking!";
    private static final String GOODBYE = "Auf Wiedersehen!";
    private static final int START = 0;
    private static final int INCREMENT = 1;

    /**
     * Prints the current list of tasks.
     *
     * @param tasks The current list of tasks to be printed.
     */
    public void printList(TaskList tasks) {
        String res = "";
        for (int i = Ui.START; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + Ui.INCREMENT, tasks.get(i));
        }
        System.out.println(res);
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Prints a farewell message.
     */
    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints a descriptive message of a command.
     *
     * @param command The command to be described.
     */
    public void printCommandMessage(Command command) {
        System.out.println(command);
    }

    /**
     * Generates a descriptive message of a command.
     *
     * @param command The command to be described.
     */
    public String getCommandMessage(Command command) {
        return command.getResponseOutput();
    }

    /**
     * Prints a descriptive message of a DukeException.
     *
     * @param e The DukeException to be described.
     */
    public void printExceptionMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Generates a descriptive message of a DukeException.
     *
     * @param e The DukeException to be described.
     */
    public String getExceptionMessage(DukeException e) {
        return e.getMessage();
    }
}
