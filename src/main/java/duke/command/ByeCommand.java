package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {

    /**
     * Executes the current command
     *
     * @param list    The task list
     * @param ui      The ui object
     * @param storage The storage object
     */
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return true;
    }

}
