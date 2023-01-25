package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

public class UnMarkCommand extends Command {
    private int index;
    public UnMarkCommand(Instruction instruction, String index) {
        super(instruction, index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        try {
            Task currTask = list.getTask(index);
            currTask.markAsUndone();
            storage.saveTask(list.getList());
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + currTask);
        } catch (TaskNumRangeException ex) {
            System.out.println(ex);
        }
    }
}
