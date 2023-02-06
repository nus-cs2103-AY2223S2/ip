package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

/**
 * An abstract Command class to deal with all types of commands from
 * user input.
 */
public abstract class Command {
    protected TaskList tasks;

    /**
     * A constructor for Command
     * @param tasks TaskList object to perform execute commands on
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes command.
     *
     * @return String to display to user
     * @throws DukeException
     */
    public abstract String execute() throws DukeException;
}
