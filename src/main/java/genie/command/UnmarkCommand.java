package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task t = tasklist.getTasks().get(index - 1);
        t.unmarkDone();
        ui.appendUnmarkDoneMessage(t);
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
