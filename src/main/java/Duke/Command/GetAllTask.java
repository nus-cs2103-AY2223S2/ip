package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class GetAllTask implements Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGetAllTaskResult(tl);
    }
}
