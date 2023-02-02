package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

/**
 * Handles the deletion of task from list of tasks
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Stores the index of the task to be deleted
     *
     * @param index Index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Stores the index of the task to be deleted
     *
     * @param fullCommand The user's input command
     * @throws DukeException If input from user does not contain a valid index
     */
    public DeleteCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("delete")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An index for a task was not given");
        }
    }

    /**
     * Removes task from list
     * Saves changes made to list in the hard disk
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     * @throws DukeException If encountering an I/O interrupt while saving data
     * @throws DukeException If given index is not in the list of tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(index);
        ui.echo("Noted. I've removed this task:");
        ui.showTask(task);
        ui.showTaskCount(tasks);
        storage.save(tasks);
    }
}
