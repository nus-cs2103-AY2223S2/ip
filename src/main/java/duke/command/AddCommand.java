package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import duke.task.Task;

/**
 * Handles the adding task to list of tasks
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
     * Adds specified task to list.
     * Save changes made to list in the hard disk.
     * Returns response for adding specified task.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return Response for adding specified task.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.save(tasks);

        return ui.getAddMessage()
                + "  " + task
                + ui.getTasksCountMessage(tasks.size());
    }
}
