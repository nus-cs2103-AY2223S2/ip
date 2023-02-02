package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

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
     * @param Tasks   the tasks
     * @param ui      the ui
     * @param storage the storage
     * @throws DukeException the duke exception
     */
    public abstract String execute(TaskList Tasks, Ui ui, Storage storage) throws DukeException;

}
