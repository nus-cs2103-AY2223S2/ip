package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmark command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
*/
public class UnmarkCommand extends Command {
    private int taskNum;


    /**
     * Constructor for UnmarkCommand
     * @param taskNum
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Unmark the task to incompleted as requested by user.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeUnmarked = tasks.getTask(taskNum - 1);
        taskToBeUnmarked.markAsIncomplete();
        return ui.showUnmarkedMsg(taskToBeUnmarked);
    }

    /**
     * Check if this command will result in termination of duke.
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
