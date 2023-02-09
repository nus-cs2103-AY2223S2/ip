package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to show list of tasks in task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
