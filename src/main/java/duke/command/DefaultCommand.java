package duke.command;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to execute default action
 */
public class DefaultCommand extends Command {

    /**
     * Exits duke if it detects bye command
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes default actions
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @throws TaskException displays error messages
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws TaskException {
        return ui.error("default");
    }
}
