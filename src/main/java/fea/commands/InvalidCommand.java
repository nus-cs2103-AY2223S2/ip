package fea.commands;

import fea.exceptions.FeaException;
import fea.exceptions.InvalidInputException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;
/**
 * InvalidCommand class that implements the Command interface.
 */

public class InvalidCommand implements Command {

    private static final String INVALID_UNKNOWN = "I'm sorry, but I don't know what that means.";

    /**
     * Throws an exception if the command is invalid.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string should not be returned and exception should be thrown instead.
     * @throws FeaException Exception is thrown due to Parser class failing to parse user input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        throw new InvalidInputException(INVALID_UNKNOWN);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
