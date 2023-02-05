package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a list all existing tasks command.
 */
public class ListCommand extends Command {

    private boolean ordered;

    public ListCommand(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Lists all existing tasks in task collection.
     * Returns String representation of all existing tasks.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return String representation of tasks in Task collection.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        if (this.ordered) {
            return ui.printTasksOrdered(tasks);
        } else {
            return ui.printTasks(tasks);
        }
    }
}
