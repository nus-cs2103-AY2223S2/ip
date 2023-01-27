package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Gives command to exit duke
 */
public class ByeCommand extends Command {

    /**
     * Exits duke if it detects bye command
     *
     * @return boolean true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits duke
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.byeMessage();
        storage.writeToFile();
    }
}
