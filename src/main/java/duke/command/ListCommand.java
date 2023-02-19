package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in the tasklist.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int counter = 1;
        String currList = "\n";

        if (tasks.getLength() == 0) {
            return ui.showEmptyListMessage();
        }

        for (int i = 0; i < tasks.getLength(); i++) {
            currList = currList + counter + "." + tasks.getTask(i).toString() + "\n";
            counter++;
        }
        return ui.showListMessage() + currList + ui.showLine();
    }

    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}
