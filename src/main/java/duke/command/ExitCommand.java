package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to exit the application
 */
public class ExitCommand extends Command {
    /**
     * Close the Ui
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        ui.closeUi();
    }

    /**
     * Returns true as this is an ExitCommand
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
