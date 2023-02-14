package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles the deletion of task from list of tasks
 * Removes specified task from list
 * Save changes made to list in the hard disk
 * Returns response for removing specified task.
 */
public class DeleteCommand extends Command {
    private final int index;

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
     * Executes command input by user.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(index);
        storage.save(tasks);

        setResponse(ui.getDeleteMessage() + "\n  "
                + task + "\n"
                + ui.getTasksCountMessage(tasks.size()));
    }
}
