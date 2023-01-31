package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command that lists the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBunny();
        ui.list(tasks);
    }

}