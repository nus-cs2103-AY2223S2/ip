package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that indicates that the command word is unrecognizable when executed.
 */
public class UnknownCommand extends Command {

    /**
     * Class constructor of UnknownCommand.
     */
    public UnknownCommand() {}

    /**
     * Returns the message that indicates that the command word is unrecognizable.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message that indicates that the command word is unrecognizable
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = "I'm sorry, but I don't know what that means :-(";
        return res;
    }

    /**
     * Returns true when the command indicates the closure of the software.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
