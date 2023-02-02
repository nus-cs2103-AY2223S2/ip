package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class for Commands, allowing other commands to extend from it
 * and add different execution steps.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
