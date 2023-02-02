package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;


/**
 * Class of FindCommand that search for keyword in the tasks list.
 */
public class FindCommand extends Command {
    private String keyword;

    private ArrayList<Task> keywords;

    public FindCommand(String cmd) {
        this.keyword = cmd.substring(5);
        this.keywords = new ArrayList<>();
    }

    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        for (Task task : tl.getTasks()) {
            if (task.getDescription().indexOf(keyword) != -1) {    // keyword is found in the task
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
