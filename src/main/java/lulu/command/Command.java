package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

/**
 * Represents a Command. A Command utilises the information from TaskList, Ui or Storage or directly edits
 * attributes within these objects to achieve its execution.
 */
public abstract class Command {
    /**
     * This method executes the command on the given parameters, depending on the type of command.
     *
     * @param tasks   the TaskList to be edited
     * @param ui      the Ui that displays messages
     * @param storage the Storage that loads data to the TaskList or writes data from the TaskList
     *                to the specified location
     * @return a String to be displayed to the user
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return true if the command is bye, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
