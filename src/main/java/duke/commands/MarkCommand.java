package duke.commands;

import duke.exceptions.DukeInvalidMarkCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code mark} command.
 */
public class MarkCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code MarkCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public MarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidMarkCommandException If the {@code mark} command is invalid.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidMarkCommandException {

        if (tokens.length != 2) {
            throw new DukeInvalidMarkCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidMarkCommandException();
        }

        // need to convert back to 0-indexed
        Task task = taskList.markTaskAsDone(taskNumber - 1);
        storage.saveTaskList(taskList);

        return "Marked:\n" + task.toString();
    }

    public boolean isByeCommand() {
        return false;
    }
}
