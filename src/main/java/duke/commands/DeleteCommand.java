package duke.commands;

import duke.exceptions.DukeInvalidDeleteCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code delete} command.
 */
public class DeleteCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code DeleteCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public DeleteCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidDeleteCommandException If the {@code delete} command is invalid.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidDeleteCommandException {

        if (tokens.length != 2) {
            throw new DukeInvalidDeleteCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidDeleteCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidDeleteCommandException();
        }

        // need to convert back to 0-indexed
        Task deletedTask = taskList.deleteTask(taskNumber - 1);
        storage.saveTaskList(taskList);

        String numTasksString = ui.formatNumberOfTasksAsString(taskList.getSize());
        return "Deleted:\n" + deletedTask + "\n" + numTasksString;
    }

    public boolean isByeCommand() {
        return false;
    }
}
