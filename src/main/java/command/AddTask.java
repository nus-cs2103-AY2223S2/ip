package command;

import shigure.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * A command adding a <code>Task</code> to a <code>TaskList</code>.
 */
public class AddTask implements Command {
    private final Task task;

    /**
     * Creates an add-task command.
     *
     * @param task task to be added
     */
    public AddTask(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks tasklist to perform the action on
     * @param ui ui to perform the action on
     * @param storage storage to perform the action on
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.print("Added this thing! That makes " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + ":");
        ui.print("  " + task.toString());
    }
}
