package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

public class UnrecognizedCmd extends Command {
    public UnrecognizedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public String execute() throws CommandExecutionError {
        throw new CommandExecutionError("Your command is as mysterious as the night. I do not understand it.");
    }
}
