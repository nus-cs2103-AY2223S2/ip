package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.exception.DukeRepeatedCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as not done yet.
 */
    public class UnmarkCommand extends IndexCommand {
    private final int LINE_NUMBER;

    /**
     * Constructor to create an unmark command.
     *
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public UnmarkCommand(String[] fullCommand) throws DukeEmptyArgumentException,
            DukeInvalidArgumentException {
        try {
            LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(LINE_NUMBER, "unmark");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of unmark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Unmark command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeIoException,
            DukeInvalidArgumentException, DukeRepeatedCommandException {
        Task t = retrieveTask(LINE_NUMBER, task, "Unmark");
        assert t != null: "Attempt to unmark an empty task";

        markTaskAsUndone(t);
        storage.updateData(LINE_NUMBER, 0);
        return ui.responseToUnmarkTaskCommand(t);
    }

    private void markTaskAsUndone(Task t) throws DukeRepeatedCommandException {
        throwExceptionIfTaskHasBeenUnmarkedBefore(t);
        t.setDone(false);
    }

    private void throwExceptionIfTaskHasBeenUnmarkedBefore(Task t) throws DukeRepeatedCommandException {
        if (t.getStatusIcon().equals("0")) {
            throw new DukeRepeatedCommandException("You already mark "
                    + getOrdinalFor(LINE_NUMBER + 1)
                    + " task as undone.");
        }
    }
}
