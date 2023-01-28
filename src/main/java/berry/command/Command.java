package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

public abstract class Command {

    private boolean isExit;

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = true;
    }

    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws BerryException;
}
