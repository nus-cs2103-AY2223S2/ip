package duke.commands;

import duke.exceptions.DukeInvalidUnmarkCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code unmark} command.
 */
public class UnmarkCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code UnmarkCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidUnmarkCommandException If the {@code unmark} command is invalid.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidUnmarkCommandException {

        if (tokens.length != 2) {
            throw new DukeInvalidUnmarkCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidUnmarkCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidUnmarkCommandException();
        }

        // need to convert back to 0-indexed
        Task task = taskList.unmarkTaskAsDone(taskNumber - 1);
        storage.saveTaskList(taskList);

        return "Unmarked:\n" + task.toString();
    }

    public boolean isByeCommand() {
        return false;
    }
}
