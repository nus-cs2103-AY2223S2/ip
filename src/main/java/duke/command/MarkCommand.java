package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deals with changing task status to done.
 * Save changes made to list in the hard disk.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Changes task status to done.
     *
     * @param fullCommand The user's input command.
     * @throws DukeException If input from user does not contain a valid index.
     */
    public MarkCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("mark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw DukeException.getErrorTaskNumberFormat();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskArrayIndexOutOfBounds();
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
        Task task = tasks.mark(index);
        storage.save(tasks);

        setResponse(ui.getMarkMessage()
                + "\n  " + task);
    }
}
