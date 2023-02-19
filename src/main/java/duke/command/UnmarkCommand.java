package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that marks a Task as not done yet.
 */
public class UnmarkCommand extends Command {
    private final String details;

    /**
     * Constructor for the UnmarkCommand class.
     * @param details contains index of the Task to be marked as not done yet.
     */
    public UnmarkCommand(String details) {
        if (details.isBlank()) {
            throw new DukeException(":( OOPS!!! The description of a task cannot be empty.");
        }
        this.details = details;
    }

    /**
     * Executes the command to mark the task as not done yet.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @return the response from Duke.
     * @throws DukeException If the user inputs an invalid reference to any of the Tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int parseInt = Integer.parseInt(this.details);
            Task taskToMark = tasks.get(parseInt - 1);
            taskToMark.markUnmark(false);
            storage.update(tasks);
            return "OK, I've marked this task as not done yet:\n" + taskToMark;
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! " + this.details
                    + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! There are less than " + this.details + " tasks.");
        }
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as UnMark does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
