package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
/**
 * Command interface that is implemented by all commands.
 */

public interface Command {

    /**
     * Executes the command.
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException If there is an exception due to invalid input.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines when to break the while loop in Duke.java.
     * @return true if the command is ByeCommand.
     */
    public boolean isExit();
}
