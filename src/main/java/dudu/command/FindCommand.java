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
    public String execute(TaskList list, Storage storage) throws DuduException {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("Here are the matching tasks in your list:\n");
//        System.out.println("Here are the matching tasks in your list:");
        for (Task task: list.getList()) {
            if (task.getDescription().contains(input)) {
                index += 1;
                sb.append(index + "." + task + "\n");
//                System.out.print(index + ".");
//                System.out.println(task);
            }
        }
        return sb.toString();
    }
}
