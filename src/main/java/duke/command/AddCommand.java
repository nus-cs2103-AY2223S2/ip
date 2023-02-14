package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles the adding task to list of tasks
 * Adds specified task to list.
 * Save changes made to list in the hard disk.
 * Returns response for adding specified task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Stores the task to add to list of tasks
     *
     * @param task Task to be added to list of tasks
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes command input by user.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check for duplicate
        for (Task task: tasks.getTasks()) {
            if (task.equals(this.task)) {
                throw new DukeException("Task is already in the TaskList");
            }
        }

        tasks.add(task);
        storage.save(tasks);

        setResponse(ui.getAddMessage() + "\n  "
                + task + "\n"
                + ui.getTasksCountMessage(tasks.size()));
    }
}
