package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Main Command class to be inherited from.
 */
public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage s);
    public abstract boolean isExit();

}
