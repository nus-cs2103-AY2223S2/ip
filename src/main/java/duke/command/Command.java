package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.TaskException;

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
     * @throws TaskException displays error messages
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws TaskException;
}