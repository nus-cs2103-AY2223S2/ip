package command;

import exception.TaskListIndexException;
import shigure.Ui;
import storage.Storage;
import task.TaskList;

public class Unmark implements Command {
    private final int idx;

    public Unmark(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(idx).unmark();
            ui.print("okay...! task unmarked as undone:");
            ui.print("  " + tasks.get(idx));
        } catch (TaskListIndexException ex) {
            ui.print("?!?!? " + ex.getMessage());
        }
    }
}
