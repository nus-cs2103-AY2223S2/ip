package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents all command types.
 * Classes with *Command inherit from this class.
 */
public abstract class Command {

    public abstract String execute(Tasks tasks, Ui ui, Storage storage);
}
