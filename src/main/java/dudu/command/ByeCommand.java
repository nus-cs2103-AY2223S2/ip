package dudu.command;

import dudu.Dudu;
import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.util.Storage;

public class ByeCommand extends Command{
    public ByeCommand(Instruction instruction) {
        super(instruction, null);
    }

    @Override
    public void execute(TaskList list, Storage storage) throws DuduException {
        System.out.println("Bye. Hope to see you again soon!");
        Dudu.exit();
    }
}
