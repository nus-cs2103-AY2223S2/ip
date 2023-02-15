package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.DuplicateException;
import dudu.task.TaskList;
import dudu.task.Todo;
import dudu.util.Storage;

/**
 * Command class for creation of todo task.
 */
public class TodoCommand extends Command {
    private String desc;

    /**
     * Constructor for todo command.
     * @param desc Description of the task.
     */
    public TodoCommand(String desc) {
        super(desc);
        this.desc = desc;
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        assert list != null;
        assert storage != null;
        Todo todo = new Todo(desc);
        boolean isDuplicate = list.getList().stream()
                .map(x -> x.getDescription())
                .anyMatch(task -> task.equals(todo.getDescription()));
        if (isDuplicate) {
            throw new DuplicateException();
        }
        storage.saveTask(list.addTask(todo));
        return "Got it. I've added this task:\n  " + todo + "\n" + list.getTotalTask() + "\n";
    }
}
