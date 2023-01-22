package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for listing the contents of a task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
