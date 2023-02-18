package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Command to list down all current Tasks.
 */
public class List extends Command {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a List command.
     * @param tasklist The list of tasks to enumerate.
     */
    public List(TaskList tasklist) {
        this.tasks = tasklist.getWholeList();
    }

    @Override
    public String execute() {
        StringBuilder res = new StringBuilder();
        res.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            res.append(String.format("%d. %s \n", (i + 1), tasks.get(i).toString()));
        }
        return res.toString();
    }
}
