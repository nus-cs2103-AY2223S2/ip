package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that returns the guide message when executed.
 */
public class GuideCommand extends Command {

    /**
     * Class constructor of GuideCommand.
     */
    public GuideCommand() {}

    /**
     * Returns the guide message about how to use Duke.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the guide message in string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = ui.showGuide();
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
