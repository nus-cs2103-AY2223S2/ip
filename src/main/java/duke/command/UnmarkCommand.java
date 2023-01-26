package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;
    private boolean exit;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        exit = false;
        return exit;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.unmarkItem(index);
    }
}
