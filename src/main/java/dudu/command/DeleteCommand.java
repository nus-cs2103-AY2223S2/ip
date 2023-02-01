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

    /**
     * Executes the delete command.
     * @param list
     * @param storage
     * @throws DuduException
     */
    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        try {
            Task currTask = list.getTask(index);
            storage.updateTask(list.delete(index));
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + currTask);
            System.out.println(list.getTotalTask());
        } catch (TaskNumRangeException ex) {
            System.out.println(ex);
        }
        return this;
    }
}
