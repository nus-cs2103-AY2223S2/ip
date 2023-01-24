package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
