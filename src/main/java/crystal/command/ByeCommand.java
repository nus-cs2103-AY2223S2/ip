package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;

/**
 * Represents the bye command when the user enters "bye".
 *
 */

public class ByeCommand extends Command {

    /**
     * Executes the bye command to print the bye message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    /**
     * Sets the exit condition to true to end the program.
     *
     */

    public boolean isExit() {
        return true;
    }
}
