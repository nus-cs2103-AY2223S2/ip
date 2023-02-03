package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

/**
 * Commands that the user wants to execute are encapsulated in this class.
 */
public abstract class Command {

    protected TaskList taskList;
    protected String lineInput;
    protected String response;

    /**
     * Constructor method.
     * @param taskList Task list to execute the command on
     * @param lineInput Command line that the user has enters
     * @throws CommandExecutionError Unable to execute command
     */
    public Command(TaskList taskList, String lineInput) {
        this.taskList = taskList;
        this.lineInput = lineInput;
    }

    /** Performs the action according to the type of command. */
    public abstract void execute() throws CommandExecutionError;

    public String getResponse() {
        return this.response;
    }
}
