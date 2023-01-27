package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulation of the command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message and close program.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printBye();
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}