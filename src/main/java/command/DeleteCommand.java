package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.deleteTask(index);
    }
}
