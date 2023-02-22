package command;

import exception.TaskListIndexException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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
            ui.printMiki("hm hmm... task #" + (idx + 1) + " deleted! "
                    + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " left.\n"
                    + "- " + task);
        } catch (TaskListIndexException ex) {
            ui.printMiki("?!?!? " + ex.getMessage());
        }
    }
}
