package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, StorageList storage);

    public boolean isExit() {
        return false;
    }
}