package command;

import exception.TaskListIndexException;
import shigure.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * A command deleting a <code>Task</code> at a specified index of a <code>TaskList</code>.
 */
public class Delete implements Command {
    private final int idx;

    /**
     * Creates a delete-task command.
     *
     * @param idx index to be deleted.
     */
    public Delete(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.delete(idx);
            ui.print("hm hmm... task #" + (idx + 1) + " deleted! "
                    + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " left.");
            ui.print("- " + task);
        } catch (TaskListIndexException ex) {
            ui.print("?!?!? " + ex.getMessage());
        }
    }
}
