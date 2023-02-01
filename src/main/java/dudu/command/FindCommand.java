package dudu.command;

import dudu.exception.DuduException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for finding task
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructor of find command
     * @param input The task name to be found
     */
    public FindCommand(String input) {
        super(input);
        this.input = input;
    }

    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        int index = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: list.getList()) {
            if (task.getDescription().contains(input)) {
                index += 1;
                System.out.print(index + ".");
                System.out.println(task);
            }
        }
        return this;
    }
}
