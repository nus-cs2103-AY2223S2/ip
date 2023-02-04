package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command from user input.
 */
public abstract class Command {
    /**
     *
     * @param tasks The TaskList instance of a list of tasks.
     * @param ui Ui instance.
     * @param storage Storage instance handling loading and saving task list to local storage.
     * @return String representing the response of the bot.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
