package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Command.
 */
public abstract class Command {

    /**
     * Boolean to indicate if command is an ExitCommand
     *
     * @return the boolean
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute command.
     *
     * @param tasks   the tasks
     * @param ui      the ui
     * @param storage the storage
     * @throws DukeException the duke exception
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
