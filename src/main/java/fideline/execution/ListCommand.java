package fideline.execution;

import fideline.task.TaskManager;
import fideline.save.Storage;
import fideline.user.Ui;

/**
 * Shows the user the list of all existing tasks.
 *
 * @author Fun Leon
 */

public class ListCommand extends Command {

    /**
     * Fetches list of all existing tasks from the taskmanager.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        String s = taskManager.getTaskListString();
        if (s.equals("")) {
            ui.emptyListMsg();
        } else {
            ui.listMsg(taskManager.getTaskListString());
        }
    }
}
