package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

public abstract class Command {
    /**
     * This method executes the command on the given parameters, depending on the type of command.
     *
     * @param tasks   the TaskList to be edited
     * @param ui      the UI that displays messages
     * @param storage the Storage that loads data to the TaskList or writes data from the TaskList
     *                to the specified location
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage);

    /**
     * @return true if the command is bye, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
