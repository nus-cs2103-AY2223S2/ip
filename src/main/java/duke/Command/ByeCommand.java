package duke.Command;

import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The bye command which is executed by duke.Utilities.Duke.
 */
public class ByeCommand extends Command {

    /**
     * Print out the farewell message, then exit duke.Utilities.Duke.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getFarewellMessage();
    }

    @Override
    public boolean isByeCommand() {
        return true;
    }
}
