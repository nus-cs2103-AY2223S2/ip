package nemo.command;

import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to List all Tasks in TaskList.
 *
 * @author Lian Kok Hai
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
