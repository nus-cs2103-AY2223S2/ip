package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.unmarkItem(index);
    }
}
