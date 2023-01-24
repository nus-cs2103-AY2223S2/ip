public class UnmarkCommand extends Command {

    private final String[] tokens;

    UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidUnmarkCommandException {

        if (this.tokens.length != 2) {
            throw new DukeInvalidUnmarkCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidUnmarkCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidUnmarkCommandException();
        }

        // need to convert back to 0-indexed
        Task task = taskList.unmarkTaskAsDone(taskNumber - 1);
        ui.showMessage("Unmarked:\n" + task.toString());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
