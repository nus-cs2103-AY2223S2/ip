package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * an abstract class that determines what to be done after receiving user input
 */
public abstract class Command {
    /**
     * method to determine what to be done after a command is given
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /** determines whether the user wants to exit the program */
    public boolean isExit() {
        return false;
    }
}
