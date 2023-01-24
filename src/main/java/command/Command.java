package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    public abstract boolean isGoodbye();
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
}
