package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
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
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        storage.writeToFile();
        return ui.byeMessage();
    }
}
