package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the command that lists all tasks in task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
