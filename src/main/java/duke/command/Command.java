package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class to allow the other commands to utilise the common methods like execute and exit.
 */
public abstract class Command {
    /**
     * Abstract method to be used by the various commands inheriting class of command.
     *
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return boolean - true or false when the prog fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, StorageList storage) throws DukeException;

    /**
     * Check if program is to be exited upon bye command.
     *
     * @return boolean - true or false according to command.
     */
    public boolean isExit() {
        return false;
    }

}
