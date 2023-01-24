public class MarkCommand extends Command {

    private final String[] tokens;

    MarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidMarkCommandException {

        if (this.tokens.length != 2) {
            throw new DukeInvalidMarkCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidMarkCommandException();
        }

        // need to convert back to 0-indexed
        Task task = taskList.markTaskAsDone(taskNumber - 1);
        ui.showMessage("Marked:\n" + task.toString());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
