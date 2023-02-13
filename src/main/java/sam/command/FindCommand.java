package sam.command;

import java.util.List;

import sam.storage.Storage;
import sam.task.Task;
import sam.task.TaskList;
import sam.ui.Dialog;

/**
 * Represents a user command to find a task.
 */
public class FindCommand extends Command {
    public FindCommand(String args) {
        super(args);
    }

    @Override
    public Result execute(TaskList tasks, Storage storage) {
        List<Task> list = tasks.findTasks(args);
        if (list.isEmpty()) {
            result.addMessage(Dialog.FIND_EMPTY.getDialog());
        } else {
            result.addMessage(String.format(Dialog.FIND.getDialog(), list.size()));
            result.addTasks(list);
        }
        return result;
    }
}
