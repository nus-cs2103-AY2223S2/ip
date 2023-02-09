package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * ListCommand class extends from Command class.
 */
public class ListCommand extends Command {

    /**
     * Constructor.
     */
    public ListCommand() {
        super(Commands.LIST);
    }

    /**
     * Displays the list of task.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }

        List<Task> immutableTaskList = tasks.getList();

        String result = "Here are the tasks in your list:";
        int index = 1;
        for (Task task : immutableTaskList) {
            result += ("\n" + index + ". " + task.toString());
            index++;
        }

        return result;
    }
}
