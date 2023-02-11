package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class UnmarkCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 7;

    public UnmarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printUnmarkTask(unmarkedTask);
    }
}
