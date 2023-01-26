package dudu.command;

import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.task.Todo;
import dudu.util.Storage;

public class TodoCommand extends Command {
    private String desc;
    public TodoCommand(String desc) {
        super(desc);
        this.desc = desc;
    }

    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        storage.saveTask(list.addTask(new Todo(desc)));
        return this;
    }
}
