package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
/**
 * InvalidCommand class that implements the Command interface.
 */

public class InvalidCommand implements Command {

    /** 
     * Throws an exception if the command is invalid.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException Exception is thrown due to Parser class failing to parse user input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
