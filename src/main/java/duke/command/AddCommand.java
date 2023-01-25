package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.tasktypes.Task;

/**
 * Represents an add task command.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an add task command
     *
     * @param task Task to be added to task collection.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Adds task to task collection.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(task);
    }
}
