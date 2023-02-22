package command;

import exception.TaskListIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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
            ui.printMiki("Yay!! Task marked as done:\n" + "  " + tasks.get(idx));
        } catch (TaskListIndexException ex) {
            ui.printMiki("?!?!? " + ex.getMessage());
        }
    }
}
