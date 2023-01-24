package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

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
