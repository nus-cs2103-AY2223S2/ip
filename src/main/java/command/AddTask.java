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
     * @param task task to be added.
     */
    public AddTask(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printMiki("Added this thing! That makes " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + ":\n" + "  " + task.toString());
    }
}
