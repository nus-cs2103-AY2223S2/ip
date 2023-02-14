package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.getTasks().get(index - 1);
        t.markDone();
        ui.showMarkDoneMessage(t);
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
