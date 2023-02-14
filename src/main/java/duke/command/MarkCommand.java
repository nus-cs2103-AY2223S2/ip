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
 * Command to mark a task as done.
 */
public class MarkCommand extends IndexCommand {
    private final int LINE_NUMBER;

    /**
     * Constructor to create a mark command.
     *
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public MarkCommand(String[] fullCommand) throws DukeEmptyArgumentException,
            DukeInvalidArgumentException {
        try {
            LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
            throwExceptionIfNegativeIndex(LINE_NUMBER, "mark");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of mark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Mark command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeIoException,
            DukeInvalidArgumentException, DukeRepeatedCommandException {
        Task t = retrieveTask(LINE_NUMBER, task, "mark");
        assert t != null : "Attempt to mark empty task";

        markTaskAsDone(t);
        storage.updateData(LINE_NUMBER, 1);
        return ui.responseToMarkTaskCommand(t);
    }

    private void markTaskAsDone(Task t) throws DukeRepeatedCommandException {
        throwExceptionIfTaskHasBeenMarkedBefore(t);
        t.setDone(true);
    }

    private void throwExceptionIfTaskHasBeenMarkedBefore(Task t) throws DukeRepeatedCommandException {
        if (t.getStatusIcon().equals("1")) {
            throw new DukeRepeatedCommandException("You already mark "
                    + getOrdinalFor(LINE_NUMBER + 1)
                    + " task as done.");
        }
    }
}
