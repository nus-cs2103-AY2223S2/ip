package command;

import tasklist.TaskList;
import exceptions.TaskException;
import storage.Storage;
import ui.Ui;

public abstract class Command {

    public abstract boolean isExit();
    public abstract void execute(TaskList tasklist, Storage storage, Ui ui) throws TaskException;
}