package twofive.command;

import twofive.data.TaskList;
import twofive.exception.TwoFiveException;
import twofive.storage.Storage;
import twofive.ui.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TwoFiveException;
}
