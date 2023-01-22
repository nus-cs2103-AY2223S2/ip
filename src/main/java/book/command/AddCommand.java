package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;
import book.task.Task;

public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        list.addTask(this.task);
        storage.save(list);
        ui.showAdd(this.task);
        ui.showTaskListSize(list);
    }
}
