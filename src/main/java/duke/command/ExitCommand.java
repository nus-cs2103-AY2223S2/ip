package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles exiting the program
 */
public class ExitCommand extends Command {

    /**
     * Exits Duke
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    /** Specifies that Duke should exit */
    @Override
    public boolean isExit() {
        return true;
    }
}
