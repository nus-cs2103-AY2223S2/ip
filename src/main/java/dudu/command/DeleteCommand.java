package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(Instruction instruction, String index) {
        super(instruction, index);
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        try {
            Task currTask = list.getTask(index);
            storage.updateTask(list.delete(index));
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + currTask);
            System.out.println(list.getTotalTask());
        } catch (TaskNumRangeException ex) {
            System.out.println(ex);
        }
    }
}
