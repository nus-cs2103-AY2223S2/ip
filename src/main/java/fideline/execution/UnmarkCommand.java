package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.InvalidArgumentException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

/**
 * Command that executes the status change of a task to undone.
 *
 * @author Fun Leon
 */
public class UnmarkCommand extends Command {

    private int taskNum;
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Changes the status of an existing task to not done.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public String execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, InvalidArgumentException {
        if (taskManager.checkTask(taskNum)) {
            String s = taskManager.unmarkTask(taskNum);
            storage.editTaskStatus(taskNum, false);
            return ui.getUnmarkMsg(s);
        } else {
            throw new InvalidArgumentException("task! (the one given does not exist!)");
        }
    }

}
