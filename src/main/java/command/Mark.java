package command;

import exception.TaskListIndexException;
import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command marking a <code>Task</code> at a specified index of a <code>TaskList</code> as complete.
 */
public class Mark implements Command {
    private final int idx;

    /**
     * Creates a mark command.
     *
     * @param idx index to be marked.
     */
    public Mark(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(idx).mark();
            ui.print("Yay!! Task marked as done:");
            ui.print("  " + tasks.get(idx));
        } catch (TaskListIndexException ex) {
            ui.print("?!?!? " + ex.getMessage());
        }
    }
}
