package duke.command;

import duke.exception.StorageFileException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */
public class AddTaskCommand extends Command {
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n%s\n"
            + "Now you have %d tasks in the list.";
    private final DukeTask task;

    /**
     * The constructor of AddTaskCommand that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddTaskCommand(DukeTask task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list, save the task list to storage,
     * and display a message to the user.
     *
     * @param tasks the task list to add the task to
     * @param ui the user interface to display a message to the user
     * @param storage the storage to save the task list to
     * @throws StorageFileException if there is an error saving the task list to storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageFileException {
        // Add the task to the task list
        tasks.addTask(this.task);
        // Save the task list to storage
        storage.saveTaskList(tasks);

        // Display a message to the user that the task was added to the task list
        String message = String.format(ADDED_TASK_MESSAGE, this.task, tasks.getNoOfTasks());
        ui.appendResponse(message);
    }
}
