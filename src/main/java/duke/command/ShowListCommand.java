package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ShowListCommand extends Command {


    /**
     * Executes the current command
     *
     * @param tasks   The task list
     * @param ui      The ui object
     * @param storage The storage object
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listUI(tasks);
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

}
