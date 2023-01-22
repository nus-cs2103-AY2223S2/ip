package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The command class represents Duke's function based on user input.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
