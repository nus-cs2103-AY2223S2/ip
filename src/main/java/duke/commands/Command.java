package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Abstract Parent class for all support commands
 */
public abstract class Command {
    /** User input with arguments */
    private String input;

    /**
     * Class constructor for this Command class using user input
     *
     * @param input User input with arguments for command execution
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the respective command
     *
     * @param tasks List of tasks as target for execution
     * @return Execution string result
     * @throws DukeException If execution encounters
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    public String getInput() {
        return input;
    }
}
