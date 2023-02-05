package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
