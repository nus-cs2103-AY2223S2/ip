package duke;

public class UnmarkCommand extends Command{
    int toggleLineNumber;

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

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeIOException, DukeInvalidArgumentException {
        if (toggleLineNumber >= task.size()) {
            throw new DukeInvalidArgumentException("There are only" + task.size()
                    + "tasks in list, but want to unmark " + toggleLineNumber + "th task.");
        }
        Task t = task.getTaskAt(toggleLineNumber);
        t.setDone(false);
        storage.updateData(toggleLineNumber, 0);
        ui.responseToUnmarkTaskCommand(t);
    }
}
