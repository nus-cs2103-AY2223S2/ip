package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import duke.task.Task;

/**
 * Handles the changing the status of task to not done
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Stores the index of the task to be marked as not done
     *
     * @param index Index of the task to be marked as not done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Stores the index of the task to be marked as not done
     *
     * @param fullCommand The user's input command
     * @throws DukeException If input from user does not contain a valid index
     */
    public UnmarkCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("unmark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An index for a task was not given");
        }
    }

    /**
     * Changes completion status of task to not done
     * Save changes made to list in the hard disk
     * Returns response for changing the status of specified task to not done.
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     * @throws DukeException If encountering an I/O interrupt while saving data
     * @throws DukeException If given index is not in the list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        storage.save(tasks);

        return ui.getUnmarkMessage()
                + "  " + task;
    }
}
