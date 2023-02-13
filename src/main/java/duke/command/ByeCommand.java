package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command that handles user exiting the program
 */
public class ByeCommand extends Command {
    /**
     * Determines that user wants to exit the program
     * @return boolean: true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints goodbye message
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye, have a nice day.";
    }
}
