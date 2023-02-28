package fea.commands;

import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;
/**
 * Command interface that is implemented by all commands.
 */
public interface Command {

    /**
     * Executes the command.
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string that contains the output message.
     * @throws FeaException If there is an exception due to invalid input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException;

    /**
     * Determines when to break the while loop in Fea.java.
     * @return true if the command is ByeCommand.
     */
    public boolean isExit();
}
