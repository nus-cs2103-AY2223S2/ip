package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String str = taskList.deleteTask(this.index);
        storage.save(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
