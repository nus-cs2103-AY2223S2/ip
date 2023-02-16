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
    private Parser parser;

    /**
     * Constructor for Ui.
     *
     * @param tasks TaskList loaded from file or empty TaskList
     */
    public Ui(TaskList tasks, Parser parser) {
        this.tasks = tasks;
        this.parser = parser;
    }

    Command acceptCommand(String command) throws DukeException {
        return parser.processCommand(command, tasks);
    }

    static String showGreetingsMessage() {
        return GREETINGS_MESSAGE;
    }

    String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}
