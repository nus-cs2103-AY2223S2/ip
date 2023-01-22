package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
<<<<<<< HEAD
 * Command for listing all tasks in a task list.
=======
 * Command for listing the contents of a task list.
>>>>>>> master
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
