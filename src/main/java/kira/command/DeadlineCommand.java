package kira.command;

import kira.storage.TaskList;
import kira.task.Deadline;
import kira.ui.Ui;

/**
 * Command for DEADLINE.
 */
public class DeadlineCommand extends Command {

    private Deadline task;

    /**
     * Constructs an executable to store a deadline task.
     * @param task deadline task to be stored
     */
    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        taskList.store(task);
        ui.storeTaskMsg(task, taskList.getTotal());
        return true;
    }

}
