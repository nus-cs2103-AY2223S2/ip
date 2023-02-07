package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes task from list of tasks when user input indicates delete.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String taskString = tasks.deleteTask(taskIndex - 1);
        storage.saveTasks(tasks);
        return Ui.getDeleteOutput(taskString, tasks);
    }
}
