package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasktypes.Task;
import duke.ui.Ui;


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
     * Returns task has been added message.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return Task has been added message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.addTask(task);
    }
}
