package duke;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int TOGGLE_LINENUMBER;

    /**
     * Constructor to create a mark command.
     *
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public MarkCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            TOGGLE_LINENUMBER = Integer.parseInt(fullCommand[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of mark command cannot be empty.");
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
        if (TOGGLE_LINENUMBER >= task.size()) {
            throw new DukeInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to mark " + (TOGGLE_LINENUMBER + 1) + "th task.");
        }

        Task t = task.getTaskAt(TOGGLE_LINENUMBER);
        t.setDone(true);
        storage.updateData(TOGGLE_LINENUMBER, 1);
        return ui.responseToMarkTaskCommand(t);
    }
}
