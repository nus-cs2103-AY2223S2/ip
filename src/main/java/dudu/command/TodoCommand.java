package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;
import dudu.exception.TaskIOException;
import dudu.task.Deadline;
import dudu.task.TaskList;
import dudu.task.Todo;
import dudu.util.Storage;

public class TodoCommand extends Command {
    private String desc;
    public TodoCommand(Instruction instruction, String desc) {
        super(instruction, desc);
        this.desc = desc;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        storage.saveTask(list.addTask(new Todo(desc)));
    }
}
