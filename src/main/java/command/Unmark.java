package command;

import exception.TaskListIndexException;
import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command marking a <code>Task</code> at a specified index of a <code>TaskList</code> as not complete.
 */
public class Unmark implements Command {
    private final int idx;

    /**
     * Creates an unmark command.
     *
     * @param idx index to be unmarked.
     */
    public Unmark(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(idx).unmark();
            ui.printMiki("okay...! task unmarked as undone:\n" + "  " + tasks.get(idx));
        } catch (TaskListIndexException ex) {
            ui.printMiki("?!?!? " + ex.getMessage());
        }
    }
}
