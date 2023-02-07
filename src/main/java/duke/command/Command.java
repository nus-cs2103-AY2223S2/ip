package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;


/**
 * Abstract class
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
