package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deals with adding tasks.
 * Save changes made to list in the hard disk.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Adds specified task to TaskList.
     *
     * @param task Task to be added to TaskList.
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
