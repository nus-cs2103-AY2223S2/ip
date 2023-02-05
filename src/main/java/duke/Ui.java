package duke;

import duke.exception.DukeException;

/**
 * A class that deals with receiving input and sending output
 * to the user.
 */
public class Ui {
    private TaskList tasks;
    private static final String GREETINGS_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you soon!";

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    String acceptCommand(String command) throws DukeException {
        String printable = Parser.processCommand(command, tasks);
        return printable;
    }

    String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}
