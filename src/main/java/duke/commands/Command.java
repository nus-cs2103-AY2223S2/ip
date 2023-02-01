package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Command {

    /**
     * Indicates the programme is not exiting
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Throws exception for invalid inputs.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
