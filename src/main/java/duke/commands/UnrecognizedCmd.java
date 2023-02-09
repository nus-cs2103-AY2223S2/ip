package duke.commands;

import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

public class UnrecognizedCmd extends Command {
    public UnrecognizedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public String execute() throws CommandExecutionError {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
