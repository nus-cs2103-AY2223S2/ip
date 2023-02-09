package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to mark tasks as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String str = taskList.markTask(this.index);
        storage.save(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
