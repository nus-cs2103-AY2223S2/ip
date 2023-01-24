package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int toDelete;

    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }
    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.deleteTask(toDelete);
    }
}
