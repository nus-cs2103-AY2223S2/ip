package dudu.command;

import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.util.Storage;

public class ListCommand extends Command {
    public ListCommand() {
        super(null);
    }
    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        list.printList();
        return this;
    }
}
