package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case to display all task
 */
public class GetAllTaskCommand extends Command {

    /**
     * Get all the task and output it as string
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.showGetAllTaskResult(tl);
    }
}
