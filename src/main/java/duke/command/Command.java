package duke.command;

import java.io.IOException;

import duke.TaskList;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;



public abstract class Command {

    /**
     * Executes the current command
     *
     * @param tasks The task list
     * @param ui The ui object
     * @param storage The storage object
     * @return The command result
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)throws
            IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException;
    /**
     * Checks if this is exit command
     */

    public abstract boolean isExit();
}
