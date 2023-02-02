package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * Adds task to list
     * Saves changes made to list in the hard disk
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     * @throws DukeException If encountering an I/O interrupt while saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.echo("Got it. I've added this task:");
        ui.showTask(task);
        ui.showTaskCount(tasks);
        storage.save(tasks);
    }
}
