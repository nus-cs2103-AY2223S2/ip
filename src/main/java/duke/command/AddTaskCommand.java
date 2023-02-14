package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for adding a subclass of Task.
 */
public abstract class AddTaskCommand implements Command {

    /**
     * Adds task to task list and saves the new task list into storage.
     *
     * @param task Task to be added.
     * @param ui User interface.
     * @param taskList Task list.
     * @param storage Storage.
     * @throws DukeException If saving to storage fails.
     */
    protected void addTask(Task task, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        ui.showLine();

        taskList.addTask(task);
        ui.showText("Got it Sir! I've added this task:");
        ui.showText(String.format("  %s", task.toString()));
        ui.showText(String.format("Now you have %d tasks in the list.", taskList.getTotalTasks()));

        ui.showLine();

        storage.save(taskList.getAllTasks());
    }
}
