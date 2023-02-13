package sam.command;

import java.util.List;

import sam.storage.Storage;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.count() == 0) {
            ui.respond(Dialog.LIST_EMPTY.getDialog());
        } else {
            List<String> list = tasks.getTasks();
            assert !list.isEmpty() : "list shouldn't be empty";
            list.add(0, Dialog.LIST.getDialog());
            ui.respond(list.toArray(new String[0]));
        }
    }
}
