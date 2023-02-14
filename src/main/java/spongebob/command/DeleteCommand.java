package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobIoException;
import spongebob.storage.Storage;

import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Deletes a task from task lisk given a specific line in the database.
 */
public class DeleteCommand extends IndexedCommand {
    private final int DELETED_LINE_NUMBER;

    /**
     * Constructor to create a delete command.
     *
     * @param fullCommand user input command.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an e empty argument.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public DeleteCommand(String[] fullCommand) throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException {
        try {
            DELETED_LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(DELETED_LINE_NUMBER, "delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobEmptyArgumentException("The description of delete command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new SpongebobInvalidArgumentException("Delete command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws SpongebobInvalidArgumentException,
            SpongebobIoException {
        Task t = retrieveTask(DELETED_LINE_NUMBER, task, "delete");
        assert t != null : "Attempt to delete empty task";

        String responseMsg = ui.responseToDeleteTaskCommand(task, DELETED_LINE_NUMBER);
        task.removeTaskAt(DELETED_LINE_NUMBER);
        storage.removeData(DELETED_LINE_NUMBER);
        return responseMsg;
    }
}
