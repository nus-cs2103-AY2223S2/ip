package command;

import java.util.List;
import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

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
     * 
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        List<Task> immutableTaskList = tasks.getList();

        ui.showTaskList(immutableTaskList);
    }
}
