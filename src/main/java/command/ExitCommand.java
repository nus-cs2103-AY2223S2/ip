package command;

import duke.DukeException;
import task.TaskList;
import ui.TextUi;

public class ExitCommand extends CommandClass {
    public ExitCommand(String command, boolean doesPrint) {
        super(command, doesPrint, true);
    }

    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        throw new DukeException("execute method not implemented for ExitCommand");
    }
}
