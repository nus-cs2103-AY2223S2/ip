package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

/**
 * A Command class for an invalid command.
 */
public class UnrecognizedCmd extends Command {
    /**
     * Constructor method.
     *
     * @param taskList Task list of the task to unmark
     * @param lineInput Command line input that the user entered
     */
    public UnrecognizedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Inform user that their command is not recognized */
    public String execute() throws CommandExecutionError {
        throw new CommandExecutionError("Your command is as mysterious as the night. I do not understand it.");
    }
}
