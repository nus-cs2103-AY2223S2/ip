package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import duke.task.Task;

/**
 * Handles the marking task as done
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Stores the index of the task to be marked as completed
     *
     * @param index Index of the task to be marked as completed
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Stores the index of the task to be marked as completed
     *
     * @param fullCommand The user's input command
     * @throws DukeException If input from user does not contain a valid index
     */
    public MarkCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("mark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An index for a task was not given");
        }
    }

    /**
     * Changes completion status of task to done
     * Save changes made to list in the hard disk
     * Returns response for changing the status of specified task to done.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return Response for adding specified task.
     * @throws DukeException If encountering an I/O interrupt while saving data
     * @throws DukeException If given index is not in the list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(index);
        storage.save(tasks);

        return ui.getMarkMessage()
                + "  " + task;
    }
}
