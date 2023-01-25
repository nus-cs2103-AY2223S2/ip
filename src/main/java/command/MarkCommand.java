package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.markItem(index);
    }
}