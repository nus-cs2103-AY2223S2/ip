package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int toMark;
    public MarkCommand(int toMark) {
        this.toMark = toMark;
    }
    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.markTask(toMark);
    }
}
