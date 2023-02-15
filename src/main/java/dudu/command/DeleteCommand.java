package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for deletion of task
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor of delete command.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(String index) {
        super(index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        assert list != null;
        assert storage != null;
        try {
            Task currTask = list.getTask(index);
            storage.updateTask(list.delete(index));
            return "Noted. I've removed this task:\n  " + currTask + list.getTotalTask() + "\n";
        } catch (TaskNumRangeException ex) {
            return ex.toString();
        }
    }
}
