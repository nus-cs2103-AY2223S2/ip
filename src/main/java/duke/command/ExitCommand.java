package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Handles tasks with exiting Duke
 */
public class ExitCommand extends Command {

    /**
     * Exits Duke.
     * Returns farewell message.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getFarewellMessage();
    }

    /** Checks if Duke should exit */
    @Override
    public boolean isExit() {
        return true;
    }
}
