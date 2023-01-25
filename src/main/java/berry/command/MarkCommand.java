package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;
import berry.exception.IndexOutOfRangeException;

public class MarkCommand extends Command {
    private static int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        if (tasks.indexWithinRange(taskIndex)) {
            ui.showMark();
            tasks.markDone(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
