package duke;

/**
 * Command to mark a task as not done yet.
 */

public class UnmarkCommand extends Command {
    int toggleLineNumber;

    /**
     * Constructor to create an unmark command.
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public UnmarkCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            toggleLineNumber = Integer.parseInt(fullCommand[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of unmark command cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Delete command require integer to execute!");
        }
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList task, Ui ui, Storage storage) throws DukeIOException, DukeInvalidArgumentException {
        if (toggleLineNumber >= task.size()) {
            throw new DukeInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to unmark " + (toggleLineNumber + 1) + "th task.");
        }
        
        Task t = task.getTaskAt(toggleLineNumber);
        t.setDone(false);
        storage.updateData(toggleLineNumber, 0);
        return ui.responseToUnmarkTaskCommand(t);
    }
}
