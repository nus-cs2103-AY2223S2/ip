package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles the changing the status of task to not done
 * Changes completion status of task to not done
 * Save changes made to list in the hard disk
 * Returns response for changing the status of specified task to not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

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
     * Executes command input by user.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        storage.save(tasks);

        setResponse(ui.getUnmarkMessage()
                + "\n  " + task);
    }
}
