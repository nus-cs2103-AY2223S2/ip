package commands;

import java.io.IOException;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is used to end the session
 */
public class ByeCommand extends Command {

    /**
     * Bids user farewell and saves task list into database
     *
     * @param taskList the task list to save
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @return {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.store(taskList);
        } catch (IOException e) {
            return "uh oh... Duke was unable to save your task list...\n"
                    + "Check your settings before running Duke to try again!";
        }
        taskList.clear();
        return "Bye! Hope you enjoyed using Duke! \n Your list awaits your return!";
    }
}
