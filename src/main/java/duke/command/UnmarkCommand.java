package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as not done yet.
 */
public class UnmarkCommand extends Command {
    private final int TOGGLE_LINE_NUMBER;

    /**
     * Constructor to create an unmark command.
     *
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public UnmarkCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            TOGGLE_LINE_NUMBER = Integer.parseInt(fullCommand[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of unmark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Delete command require integer to execute!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeIoException, DukeInvalidArgumentException {
        if (TOGGLE_LINE_NUMBER >= task.size()) {
            throw new DukeInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to unmark " + (TOGGLE_LINE_NUMBER + 1) + "th task.");
        }
        Task t = task.getTaskAt(TOGGLE_LINE_NUMBER);
        assert t != null: "Attempt to unmark an empty task";
        t.setDone(false);
        storage.updateData(TOGGLE_LINE_NUMBER, 0);
        return ui.responseToUnmarkTaskCommand(t);
    }
}
