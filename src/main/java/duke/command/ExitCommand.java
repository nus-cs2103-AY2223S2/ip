package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits duke main program when user input states bye.
 */
public class ExitCommand extends Command {
    /**
     * Exits from main program.
     *
     * @param tasks List of tasks.
     * @param storage object that handles all Storage actions.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getExitOutput();
    }

    @Override
    public String toString() {
        return String.format("Exits");
    }
}
