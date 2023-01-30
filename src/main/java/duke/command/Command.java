package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * An abstract class to enforce a structure
 * for command classes.
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException, IOException;


    public abstract boolean isExit();
}
