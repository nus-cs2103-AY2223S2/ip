package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * > This class is a Command and it is used to list the contents of the current directory
 */
public class List extends Command{
    /**
     * This function takes in a TaskList, Storage, and Ui object, and lists all the tasks in the TaskList
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The UI object that is used to interact with the user.
     * @param storage The storage object that contains the tasks.
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }
}
