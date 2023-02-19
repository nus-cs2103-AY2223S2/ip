package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.InvalidArgumentException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

/**
 * Command that executes the status change of a task to done.
 *
 * @author Fun Leon
 */
public class MarkCommand extends Command {

    private int taskNum;
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Changes the status of an existing task to done.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public String execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, InvalidArgumentException {
        if (!taskManager.isValidTask(taskNum)) {
            throw new InvalidArgumentException("task");
        }
        String s = taskManager.getTaskString(taskNum);
        taskManager.markTask(taskNum);
        storage.markTask(taskNum);
        return ui.getMarkMsg(s);
    }


}

