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
    public Command execute(TaskList list, Storage storage) throws DuduException {
        try {
            Task currTask = list.getTask(index);
            currTask.markAsUndone();
            storage.saveTask(list.getList());
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + currTask);
        } catch (TaskNumRangeException ex) {
            System.out.println(ex);
        } catch (DuduException ex) {
            System.out.println(ex);
        }
        return this;
    }
}
