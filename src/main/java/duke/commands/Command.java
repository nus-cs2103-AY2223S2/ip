package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Main Command class to be inherited from.
 */
public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage s);
    public abstract boolean isExit();

}
