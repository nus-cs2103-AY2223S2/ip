package sam.command;

import java.util.List;

import sam.storage.Storage;
import sam.task.Task;
import sam.task.TaskList;
import sam.ui.Dialog;
import sam.ui.Ui;

/**
 * Represents a user command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public Result execute(TaskList tasks, Storage storage) {
        if (tasks.count() == 0) {
            result.addMessage(Dialog.LIST_EMPTY.getDialog());
        } else {
            List<Task> list = tasks.getTasks();
            assert !list.isEmpty() : "list shouldn't be empty";
            result.addMessage(Dialog.LIST.getDialog());
            result.addTaskList(list);
        }
        return result;
    }
}
