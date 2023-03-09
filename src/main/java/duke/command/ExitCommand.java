package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(taskList);
        ui.close();
        return " ";
    }
    public boolean isExit() {
        return true;
    }
}
