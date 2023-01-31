package duke.command;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to execute an action
 */
public abstract class Command {

    /**
     * Exits duke if it detects bye command
     */
    public abstract boolean isExit();

    /**
     * Executes task actions
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @return instruction successfully set
     * @throws TaskException displays error messages
     */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws TaskException;
}
