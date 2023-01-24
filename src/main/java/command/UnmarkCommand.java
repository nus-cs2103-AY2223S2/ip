package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int toUnmark;
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark;
    }
    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.unmarkTask(toUnmark);
    }
}
