package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Contains a set of instructions to be executed.
 * Commands can display through the UI, save a list using a Storage,
 * and handle a task list.
 */
public abstract class Command {

    /**
     * Performs the task detailed by the command.
     * @param tasks
     * @param ui
     * @param storage
     * @throws Exception
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isBye() {
        return false;
    }
}
