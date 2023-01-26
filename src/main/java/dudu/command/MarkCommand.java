package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(String index) {
        super(index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        try {
            Task currTask = list.getTask(index);
            currTask.markAsDone();
            storage.saveTask(list.getList());
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + currTask);
        } catch (TaskNumRangeException ex) {
            System.out.println(ex);
        } catch (DuduException ex) {
            System.out.println(ex);
        }
        return this;
    }
}
