package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public abstract class Command {
    public boolean shouldContinue() {
        return true;
    }
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);
}
