package duke.commands;

import duke.exceptions.DukeEmptyListException;
import duke.tasks.Task;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeEmptyListException {
        if (taskList.isEmpty()) {
            throw new DukeEmptyListException();
        }

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            String indexString = Integer.toString(i + 1);
            ui.showMessage(indexString + ". " + task.toString());
        }
    }

    public boolean isByeCommand() {
        return false;
    }
}
