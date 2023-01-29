package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * For handling the actions to take
 * when exiting the application.
 */
public class ByeCommand extends Command {

    /**
     * For executing the actions taken for exiting
     * the app.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();

    }

    /**
     * To close the loop in the program.
     *
     * @return true, so that program terminates.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
