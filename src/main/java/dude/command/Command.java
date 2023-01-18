package dude.command;

import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public abstract class Command {
    private boolean isExit;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
