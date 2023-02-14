package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete a task from task lisk given a specific line in the database.
 */
    public class DeleteCommand extends IndexCommand {
    private final int DELETED_LINE_NUMBER;

    /**
     * Constructor to create a delete command.
     *
     * @param fullCommand user input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an e empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public DeleteCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            DELETED_LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(DELETED_LINE_NUMBER, "delete");
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
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException,
            DukeIoException {
        Task t = retrieveTask(DELETED_LINE_NUMBER, task, "delete");
        assert t != null : "Attempt to delete empty task";

        String responseMsg = ui.responseToDeleteTaskCommand(task, DELETED_LINE_NUMBER);
        task.removeTaskAt(DELETED_LINE_NUMBER);
        storage.removeData(DELETED_LINE_NUMBER);
        return responseMsg;
    }
}
