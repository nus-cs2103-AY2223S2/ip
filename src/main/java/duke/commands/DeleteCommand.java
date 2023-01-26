package duke.commands;

import duke.exceptions.DukeInvalidDeleteCommandException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DeleteCommand extends Command {

    private final String[] tokens;

    public DeleteCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidDeleteCommandException {

        if (tokens.length != 2) {
            throw new DukeInvalidDeleteCommandException();
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidDeleteCommandException();
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeInvalidDeleteCommandException();
        }

        // need to convert back to 0-indexed
        Task deletedTask = taskList.deleteTask(taskNumber - 1);
        ui.showMessage("Deleted:\n" + deletedTask.toString());
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
