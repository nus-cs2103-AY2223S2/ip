package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command that handles listing all tasks
 */
public class ListCommand extends Command {
    /**
     * Prints in style all tasks in the TaskList
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.changeToFormat(tasks.toString());
    }
}
