package duke.commands;

import duke.exceptions.DukeInvalidUnmarkCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class UnmarkCommand extends Command {

    private final String[] tokens;

    public UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidUnmarkCommandException {

        if (tokens.length != 2) {
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
