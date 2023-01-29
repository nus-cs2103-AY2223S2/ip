package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case to display all task
 */
public class GetAllTaskCommand extends Command {

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.showGetAllTaskResult(tl);
    }
}
