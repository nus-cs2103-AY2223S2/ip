package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(Parser.stringToInt(textCmd.substring(7)));
        return ui.printUnmarkTask(unmarkedTask);
    }
}
