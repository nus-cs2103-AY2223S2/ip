package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds task when user input states add.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return Ui.getAddOutput(task, tasks);
    }

    @Override
    public String toString() {
        return String.format("Add task %s", task.toString());
    }
}
