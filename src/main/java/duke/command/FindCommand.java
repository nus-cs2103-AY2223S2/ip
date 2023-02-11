package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class of FindCommand that search for keyword in the tasks list.
 */
public class FindCommand extends Command {
    private String keyword;
    private ArrayList<Task> keywords;

    /**
     * Constructor for FindCommand.
     *
     * @param cmd the command given by the user.
     */
    public FindCommand(String cmd) {
        this.keyword = cmd.substring(5);
        this.keywords = new ArrayList<>();
    }

    /**
     * Overrides execute method from the abstract class of Command.
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        for (Task task : tl.getTasks()) {
            if (task.getDescription().indexOf(keyword) != -1) { // keyword is found in the task
                this.keywords.add(task);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("Here are the matching tasks in your list: \n");
        int counter = 1;
        for (Task task : this.keywords) {
            res.append(counter++ + ". " + task + "\n");
        }
        return res.toString();
    }
}
