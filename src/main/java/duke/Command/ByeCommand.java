package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The bye command which is executed by Duke.
 */
public class ByeCommand extends Command {

    /**
     * Print out the farewell message, then exit Duke.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.getFarewellMessage();
        System.exit(0);
    }
}
