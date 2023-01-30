package duke.command;

import duke.*;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command {

    /**
     * Executes the current command
     *
     * @param tasks The task list
     * @param ui The ui object
     * @param storage The storage object
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)throws FileNotFoundException,
        IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException;
    /**
     * Checks if this is exit command
     */
    public abstract boolean isExit();
}
