package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * An abstract class to represent the Commands Duke can execute.
 * The execution of a Command is displayed to the user with the UI handler,
 * and any changes made to the TaskList as a result of the Command is saved.
 */
public abstract class Command {
    public boolean isBye() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}