package duke.command;

import duke.*;
import duke.tasks.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(Parser.stringToInt(textCmd.substring(7)));
        ui.printUnmarkTask(unmarkedTask);

    }
}
