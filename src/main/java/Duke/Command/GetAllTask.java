package Duke.Command;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Class in charge of listing all Task
 */
public class GetAllTask implements Command {

    /**
     * Get all Task in TaskList and output using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGetAllTaskResult(tl);
    }
}
