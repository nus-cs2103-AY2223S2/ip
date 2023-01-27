package command;

import exception.TaskListIndexException;
import shigure.Ui;
import storage.Storage;
import task.TaskList;

public class Mark implements Command {
    private final int idx;

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
