package command;

import io.Storage;
import io.Ui;
import task.TaskList;

/**
 * Manages the error message to be shown in the UI.
 */
public class Error implements Command {
    private final String msg;

    private Error(String msg) {
        this.msg = msg;
    }

    /**
     * @param msg Error message to be shown.
     * @return Error command.
     */
    public static Error of(String msg) {
        return new Error(msg);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showError(msg);
    }
}
