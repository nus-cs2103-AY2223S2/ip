package duke.command;

import java.io.IOException;

import duke.TaskList;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;


public abstract class Command {

    /**
     * Executes the current command
     *
     * @param tasks   The task list
     * @param ui      The ui object
     * @param storage The storage object
     * @return The command result
     * @throws IllegalArgumentException Throws an exception when the input command does not exist
     * @throws DukeException            Throws DukeException of a specific massage
     * @throws IOException              Throws an exception when writing to the text file fails
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws
            IllegalArgumentException, DukeException, IOException;

    /**
     * Checks if this is exit command
     */

    public abstract boolean isExit();
}
