package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for listing all tasks in a task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
