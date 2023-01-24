package duke;

public class MarkCommand extends Command{
    int toggleLineNumber;

    public MarkCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            toggleLineNumber = Integer.parseInt(fullCommand[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of mark command cannot be empty.");
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
                    + "tasks in list, but want to mark " + toggleLineNumber + "th task.");
        }
        Task t = task.getTaskAt(toggleLineNumber);
        t.setDone(true);
        storage.updateData(toggleLineNumber, 1);
        ui.responseToMarkTaskCommand(t);
    }
}
