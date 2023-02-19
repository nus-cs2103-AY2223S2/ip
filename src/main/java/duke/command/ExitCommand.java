package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exit command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class ExitCommand extends Command {

    /**
     * Terminates duke program.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }

    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */

    public boolean isExit() {
        return true;
    }
}
