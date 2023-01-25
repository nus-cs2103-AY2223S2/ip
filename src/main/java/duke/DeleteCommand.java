package duke;

/**
 * Delete a task from task lisk given a specific line in the database.
 */
public class DeleteCommand extends Command{
    private int deletedLineNumber;

    /**
     * Constructor to create a delete command.
     *
     * @param fullCommand user input command.
     * @throws DukeEmptyArgumentException
     * @throws DukeInvalidArgumentException
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

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIOException {
        if (deletedLineNumber >= task.size()) {
            throw new DukeInvalidArgumentException("There are only" + task.size()
                    + "tasks in list, but want to delete " + deletedLineNumber + "th task.");
        }
        ui.responseToDeleteTaskCommand(task, deletedLineNumber);
        task.removeTaskAt(deletedLineNumber);
        storage.removeData(deletedLineNumber);
    }
}
