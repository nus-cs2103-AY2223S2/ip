package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int i) {
        this.index = i;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task t = tasklist.get(index);
        tasklist.remove(t);

        ui.printResponse("The following task has been removed: \n    " + t
                        + "\n" + tasklist.getSizeAsString());
    }
    
}
