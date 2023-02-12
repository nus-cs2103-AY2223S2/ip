package clippy.command;

import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * An abstract class that encapsulates commands which add Tasks to TaskList.
 *
 * @author chunzkok
 */
public abstract class AddCommand extends Command {

    /**
     * Prints nicely formatted text regarding the newly added task
     * that is at the back of the TaskList.
     *
     * @param taskList The TaskList instance used for the current run
     * @param ui The Ui instance used for the current run
     */
    protected void printCreatedTaskStatus(TaskList taskList, Ui ui) {
        ui.prettyPrint("Got it! I've added this task:"
                + "\n" + taskList.getLastTask().toString() + "\n"
                + String.format("Now you have %d task%s in the list.",
                taskList.getSize(), taskList.getSize() == 1 ? "" : "s")
                + "\n");
    }
}
