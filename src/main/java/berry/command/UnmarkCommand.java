package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;
import berry.exception.IndexOutOfRangeException;

public class UnmarkCommand extends Command {
    private static int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        if (tasks.isIndexWithinRange(taskIndex)) {
            ui.showUnmark();
            tasks.markNotDone(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
