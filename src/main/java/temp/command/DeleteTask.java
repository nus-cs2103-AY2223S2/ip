package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteTask implements Command {
    private int index;
    public DeleteTask(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        storage.delete(index);
        Task t = tl.getTask(index - 1);
        tl.remove(index - 1);
        ui.showDeleteResult(t.toString(), tl.getSize());
    }
}
