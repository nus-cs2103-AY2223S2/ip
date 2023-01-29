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
     * List all the tasks in the tasklist.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int counter = 1;
        ui.showListMessage();
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println(counter + "." + tasks.getTask(i).toString());
            counter++;
        }
    }

    /**
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}
