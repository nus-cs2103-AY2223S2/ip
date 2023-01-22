package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;
import book.task.Task;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        Task task = list.deleteTask(this.index);
        storage.save(list);
        ui.showDelete(task);
        ui.showTaskListSize(list);
    }
}
