package duke.command;

import duke.tasks.Task;
import duke.Parser;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
