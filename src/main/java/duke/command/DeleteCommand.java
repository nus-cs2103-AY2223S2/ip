package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the delete command.
     *
     * @param taskNum
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task in the tasklist and update it in the txt file.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNum - 1 >= tasks.getLength() || taskNum == 0) {
            return ui.showNonExistentTask(tasks.getLength());
        } else {
            Task taskToBeDeleted = tasks.getTask(taskNum - 1);
            tasks.delete(taskNum - 1);
            storage.delete(taskNum);
            return ui.showDeleteMessage(taskToBeDeleted, String.valueOf(tasks.getLength()));
        }
    }

    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
