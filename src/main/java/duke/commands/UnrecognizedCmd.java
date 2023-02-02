package duke.commands;

import duke.Duke;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

public class UnrecognizedCmd extends Command {
    public UnrecognizedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public void execute(Duke duke) throws CommandExecutionError {
        duke.sendResponse("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
