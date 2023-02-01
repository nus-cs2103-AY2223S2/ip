package dudu.command;

import dudu.exception.DuduException;
import dudu.task.Task;
import dudu.task.TaskList;
import dudu.util.Storage;

import java.util.ArrayList;
import java.util.Arrays;

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
    public Command execute(TaskList list, Storage storage) throws DuduException {
        int index = 0;
        ArrayList<Task> visited = new ArrayList<>();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: list.getList()) {
            for (String input: inputArr) {
                if (task.getDescription().contains(input.strip()) && !visited.contains(task)) {
                    visited.add(task);
                    index += 1;
                    System.out.print(index + ".");
                    System.out.println(task);
                }
            }
        }
        return this;
    }
}
