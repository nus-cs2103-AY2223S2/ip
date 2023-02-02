package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits duke main program when user input indicates bye.
 */
public class ExitCommand extends Command {
    /**
     * Exits from main program.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.isExit = true;
        return ui.getExitOutput();
    }
}
