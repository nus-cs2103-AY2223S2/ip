package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that deletes a Task from the current TaskList.
 */
public class DeleteCommand extends Command {
    private final String details;

    /**
     * Constructor for the DeleteCommand class.
     * @param details contains index of the Task to be deleted.
     */
    public DeleteCommand(String details) {
        if (details.isBlank()) {
            throw new DukeException(":( OOPS!!! The description of a new task cannot be empty.");
        }
        this.details = details;
    }

    /**
     * Executes the command to delete the task.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @return the response from Duke.
     * @throws DukeException If the user inputs an invalid reference to any of the Tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String dukeResponse = "";
        try {
            int parseInt = Integer.parseInt(details);
            Task removedTask = tasks.remove(parseInt - 1);
            assert removedTask != null : "removedTask cannot be null.";
            storage.update(tasks);;
            dukeResponse = "Noted. I've removed this task:\n" + removedTask + "\n";
            dukeResponse += "Now you have " + tasks.size() + " tasks in the list.";
            return dukeResponse;
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! " + details + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! There are less than " + details + " tasks.");
        }
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as DeleteCommand does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
