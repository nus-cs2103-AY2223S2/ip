package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete a task from task lisk given a specific line in the database.
 */
public class DeleteCommand extends Command {
    private final int deletedLineNumber;

    /**
     * Constructor to create a delete command.
     *
     * @param fullCommand user input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an e empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public DeleteCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            deletedLineNumber = Integer.parseInt(fullCommand[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of delete command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Delete command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIoException {
        if (deletedLineNumber >= task.size()) {
            throw new DukeInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to delete " + (deletedLineNumber + 1)+ "th task.");
        }
        String responseMsg = ui.responseToDeleteTaskCommand(task, deletedLineNumber);
        task.removeTaskAt(deletedLineNumber);
        storage.removeData(deletedLineNumber);
        return responseMsg;
    }
}
