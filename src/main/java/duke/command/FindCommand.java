package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


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
     * Override execute method from the abstract class of Command.
     *
     * @param tl       - list of tasks.
     * @param ui       - interface.
     * @param storage  - harddisk store using textfile.
     * @return boolean - returns true.
     */
    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        for (Task task : tl.getTasks()) {
            if (task.getDescription().indexOf(keyword) != -1) { // keyword is found in the task
                this.keywords.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list: ");
        for (Task task : this.keywords) {
            System.out.println(task);
        }
        return true;
    }
}
