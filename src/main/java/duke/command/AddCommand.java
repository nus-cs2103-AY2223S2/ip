package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds task when user input indicates add.
 */
public class AddCommand extends Command {
    private final Task TASK;

    public AddCommand(Task task) {
        this.TASK = task;
    }

    /**
     * Adds task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.TASK);
        storage.saveTasks(tasks);
        ui.showAdd(this.TASK, tasks);
    }
}
