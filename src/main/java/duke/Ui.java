package duke;

import duke.exception.DukeException;

/**
 * A class that deals with receiving input and sending output
 * to the user.
 */
public class Ui {
    private TaskList tasks;
    private static final String GREETINGS_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";

    /**
     * Constructor for Ui.
     *
     * @param tasks TaskList loaded from file or empty TaskList
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    String acceptCommand(String command) throws DukeException {
        String printable = Parser.processCommand(command, tasks);
        return printable;
    }

    static String showGreetingsMessage() {
        return GREETINGS_MESSAGE;
    }

    String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}
