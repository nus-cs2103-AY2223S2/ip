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
 * Command to mark a task as done.
 */
public class MarkCommand extends IndexedCommand {
    private final int LINE_NUMBER;

    /**
     * Constructor to create a mark command.
     *
     * @param fullCommand User input command.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public MarkCommand(String[] fullCommand) throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException {
        try {
            LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(LINE_NUMBER, "mark");
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobEmptyArgumentException("The description of mark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new SpongebobInvalidArgumentException("Mark command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws SpongebobIoException,
            SpongebobInvalidArgumentException, SpongebobRepeatedCommandException {
        Task t = retrieveTask(LINE_NUMBER, task, "mark");
        assert t != null : "Attempt to mark empty task";

        markTaskAsDone(t);
        storage.updateData(LINE_NUMBER, 1);
        return ui.responseToMarkTaskCommand(t);
    }

    private void markTaskAsDone(Task t) throws SpongebobRepeatedCommandException {
        throwExceptionIfTaskHasBeenMarkedBefore(t);
        t.setDone(true);
    }

    private void throwExceptionIfTaskHasBeenMarkedBefore(Task t) throws SpongebobRepeatedCommandException {
        if (t.getStatusIcon().equals("1")) {
            throw new SpongebobRepeatedCommandException("You already mark "
                    + getOrdinalFor(LINE_NUMBER + 1)
                    + " task as done.");
        }
    }
}
