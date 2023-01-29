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
     * Terminate duke program.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        isExit();
    }

    /**
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */

    public boolean isExit() {
        return true;
    }
}
