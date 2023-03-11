package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for marking task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for mark command.
     * @param index The index of task in the list to be mark as done
     */
    public MarkCommand(String index) {
        super(index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        assert list != null;
        assert storage != null;
        try {
            Task currTask = list.getTask(index);
            currTask.markAsDone();
            storage.saveTask(list.getList());
            return "Nice! I've marked this task as done:\n  " + currTask + "\n";
        } catch (TaskNumRangeException ex) {
            return ex.toString();
        } catch (DuduException ex) {
            return ex.toString();
        }
    }
}
