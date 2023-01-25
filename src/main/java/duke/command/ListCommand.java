package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a list all existing tasks command.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Lists all existing tasks in task collection.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTasks(tasks);
    }
}
