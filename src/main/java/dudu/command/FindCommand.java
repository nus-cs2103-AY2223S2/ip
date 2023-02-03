package dudu.command;

import java.util.ArrayList;

import dudu.exception.DuduException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;



/**
 * Command class for finding task
 */
public class FindCommand extends Command {
    private String[] inputArr;

    /**
     * Constructor of find command
     * @param inputArr The list of task name to be found
     */
    public FindCommand(String... inputArr) {
        super(inputArr[0]);
        this.inputArr = inputArr;

    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        ArrayList<Task> visited = new ArrayList<>();
        sb.append("Here are the matching tasks in your list:\n");
        for (Task task: list.getList()) {
            for (String input: inputArr) {
                if (task.getDescription().contains(input.strip()) && !visited.contains(task)) {
                    visited.add(task);
                    index += 1;
                    sb.append(index + "." + task + "\n");
                }
            }
        }
        return sb.toString();
    }
}
