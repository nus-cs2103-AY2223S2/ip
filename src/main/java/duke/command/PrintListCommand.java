package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that returns the list of tasks when executed.
 */
public class PrintListCommand extends Command {

    /**
     * Class constructor of PrintListCommand
     */
    public PrintListCommand() {}

    /**
     * Returns the list of tasks in the TaskList in string.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the list of tasks in the TaskList in string
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = tasks.toString();
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
