package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes task from list of tasks when user input indicates delete.
 */
public class DeleteCommand extends Command {
    private final int TASK_INDEX;

    public DeleteCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Deletes task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = tasks.deleteTask(this.TASK_INDEX - 1);
        storage.saveTasks(tasks);
        ui.showDelete(taskString, tasks);
    }
}
