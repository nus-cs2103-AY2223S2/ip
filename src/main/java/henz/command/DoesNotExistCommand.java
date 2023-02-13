package henz.command;

import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.ui.Ui;

/**
 * DoesNotExistCommand class extends Command class.
 * This class represenst commands that are not recognised by Duke.
 */
public class DoesNotExistCommand extends Command {

    /**
     * Constructor.
     */
    public DoesNotExistCommand() {
        super("");
    }

    /**
     * Displays message to tell user that command is not recognised.
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
