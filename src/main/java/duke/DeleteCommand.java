package duke;

public class DeleteCommand extends Command{
    private int deletedLineNumber;

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
        task.removeTaskAt(deletedLineNumber);
        storage.removeData(deletedLineNumber);
        ui.responseToDeleteTaskCommand(task, deletedLineNumber);
    }
}
