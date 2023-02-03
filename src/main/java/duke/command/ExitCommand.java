package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(taskList);
        ui.showMessage("Bye. Hope to see you again soon!");
        ui.close();
    }
    public boolean isExit() {
        return true;
    }
}