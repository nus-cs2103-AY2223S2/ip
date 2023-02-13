package sam.command;

import java.util.List;

import sam.Dialog;
import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

/**
 * Represents a user command to find a task.
 */
public class FindCommand extends Command {
    public FindCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> list = tasks.findTasks(args);
        if (list.isEmpty()) {
            ui.respond(Dialog.FIND_EMPTY.getDialog());
        } else {
            list.add(0, String.format(Dialog.FIND.getDialog(), list.size()));
            ui.respond(list.toArray(new String[0]));
        }
    }
}
