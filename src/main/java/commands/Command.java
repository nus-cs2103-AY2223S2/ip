package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
