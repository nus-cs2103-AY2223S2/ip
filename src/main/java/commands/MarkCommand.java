package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

public class MarkCommand extends Command {
    private boolean toMark;
    private int index;
    public MarkCommand(boolean toMark, int i) {
        this.toMark = toMark;
        this.index = i;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task t = tasklist.get(index);
        ui.printResponse(toMark ? t.mark() : t.unmark());
    }
}
