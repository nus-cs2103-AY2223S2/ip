package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List command to show the current task list.
 */
public class ListCommand extends Command {

    /**
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return
     */
    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.printList();
        return true;
    }

}

