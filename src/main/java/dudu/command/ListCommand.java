package dudu.command;

import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for list down all the tasks
 */
public class ListCommand extends Command {
    /**
     * Constructor for list command.
     */
    public ListCommand() {
        super(null);
    }
    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        return list.printList();
    }
}
