package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * A class that deals with receiving input and sending output
 * to the user.
 */
public class Ui {
    private static final String GREETINGS_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private TaskList tasks;

    /**
     * Constructor for Ui.
     *
     * @param tasks TaskList loaded from file or empty TaskList
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    Command acceptCommand(String command) throws DukeException {
        return Parser.processCommand(command, tasks);
    }

    static String showGreetingsMessage() {
        return GREETINGS_MESSAGE;
    }

    String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}
