package duke.Command;

import duke.Exception.DukeException;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The abstraction behind each command executed by Duke.
 */
public abstract class Command {

    /**
     * Performs the corresponding execution of the command.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     * @throws DukeException The exception thrown when there is an error in executing a command.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

}
