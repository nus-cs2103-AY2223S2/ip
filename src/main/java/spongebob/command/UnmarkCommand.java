package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobIoException;
import spongebob.exception.SpongebobRepeatedCommandException;

import spongebob.storage.Storage;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Command to mark a task as not done yet.
 */
public class UnmarkCommand extends IndexedCommand {
    private final int LINE_NUMBER;

    /**
     * Constructor to create an unmark command.
     *
     * @param fullCommand User input command.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public UnmarkCommand(String[] fullCommand) throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException {
        try {
            LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(LINE_NUMBER, "unmark");
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobEmptyArgumentException("The description of unmark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new SpongebobInvalidArgumentException("Unmark command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws SpongebobIoException,
            SpongebobInvalidArgumentException, SpongebobRepeatedCommandException {
        Task t = retrieveTask(LINE_NUMBER, task, "Unmark");
        assert t != null : "Attempt to unmark an empty task";

        markTaskAsUndone(t);
        storage.updateData(LINE_NUMBER, 0);
        return ui.responseToUnmarkTaskCommand(t);
    }

    private void markTaskAsUndone(Task t) throws SpongebobRepeatedCommandException {
        throwExceptionIfTaskHasBeenUnmarkedBefore(t);
        t.setDone(false);
    }

    private void throwExceptionIfTaskHasBeenUnmarkedBefore(Task t) throws SpongebobRepeatedCommandException {
        if (t.getStatusIcon().equals("0")) {
            throw new SpongebobRepeatedCommandException("You already mark "
                    + getOrdinalFor(LINE_NUMBER + 1)
                    + " task as undone.");
        }
    }
}
