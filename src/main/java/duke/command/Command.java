package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    private boolean isTerminated = false;

    public boolean isTerminated() {
        return isTerminated;
    };

    public void terminate() {
        this.isTerminated = true;
    }

    public abstract void initCommand(TaskList tasks, Ui ui, Storage storage);
}


