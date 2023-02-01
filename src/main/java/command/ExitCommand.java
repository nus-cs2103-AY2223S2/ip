package command;

import main.TaskList;
import main.Storage;
import main.DukeException;
import main.Ui;

/**
 * Exits the programme.
 */
public class ExitCommand extends Command {

    /**
     * Returns true to indicate that user calls for exit.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Output exit message to users.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Ui.outputExit();
    }

}
