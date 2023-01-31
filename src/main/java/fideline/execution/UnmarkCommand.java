package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.InvalidArgumentException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

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
    public void execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, InvalidArgumentException {
        if (taskManager.checkTask(taskNum)) {
            String s = taskManager.unmarkTask(taskNum);
            storage.editTaskStatus(taskNum, false);
            ui.unmarkMsg(s);
        } else {
            throw new InvalidArgumentException("task! (the one given does not exist!)");
        }
    }


}

