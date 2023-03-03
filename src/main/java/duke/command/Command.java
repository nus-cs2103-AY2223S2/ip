package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

/**
 * Command class which has an execute method to execute the command.
 */
public abstract class Command {
    protected String input;

    /**
     * Constructor for Command
     *
     * @param input The user input.
     */
    public Command(String input) {
        assert !input.isEmpty();
        this.input = input;
    }

    /**
     * Executes the Command. Implemented in subclasses of Command depending on the command to be executed.
     *
     * @param tasks The TaskList the command is executed upon.
     */
    public abstract TaskList execute(TaskList tasks) throws DukeException;
}
