package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;



/**
 * Mark command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class MarkCommand extends Command {
    private int taskNum;


    /**
     * Constructor for MarkCommand.
     *
     * @param taskNum
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task as completed as requested by user.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNum - 1 >= tasks.getLength()) {
            return ui.showNonExistentTask(tasks.getLength());
        } else {
            Task taskToBeMarked = tasks.getTask(taskNum - 1);
            assert taskNum - 1 >= 0 : "taskNum should never be less than 0";
            taskToBeMarked.markAsDone();
            return ui.showMarkedMsg(taskToBeMarked);
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
