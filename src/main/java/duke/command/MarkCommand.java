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
     * Mark the task as completed as requested by user.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeMarked = tasks.getTask(taskNum - 1);
        taskToBeMarked.markAsDone();
        return ui.showMarkedMsg(taskToBeMarked);
    }

    /**
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
