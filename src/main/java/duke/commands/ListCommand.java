package duke.commands;

import duke.duke.Ui;
import duke.exceptions.StorerEmptyException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * A command for the keyword "list".
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks.
     * @param tasks
     * @param ui
     * @param storage
     * @throws StorerEmptyException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorerEmptyException {
        ui.display(tasks.getTaskStrings());
    }
}
