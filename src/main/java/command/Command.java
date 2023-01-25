package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasklist, Storage storage, Ui ui);
}