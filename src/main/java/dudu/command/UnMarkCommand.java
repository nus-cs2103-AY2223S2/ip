package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for marking task as undone.
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for unmark command.
     * @param index Index of the task which will be mark as undone.
     */
    public UnMarkCommand(String index) {
        super(index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        assert list != null;
        assert storage != null;
        try {
            Task currTask = list.getTask(index);
            currTask.markAsUndone();
            storage.saveTask(list.getList());
            return "OK, I've marked this task as not done yet:\n  " + currTask + "\n";
        } catch (TaskNumRangeException ex) {
            return ex.toString();
        } catch (DuduException ex) {
            return ex.toString();
        }
    }
}
