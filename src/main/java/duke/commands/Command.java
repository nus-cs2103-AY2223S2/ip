package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

/**
 * Abstract command class for all duke.Duke commands.
 */
public abstract class Command {
    /** The status of the command after execution. **/
    protected String commandStatus;

    /**
     * Executes the respective command.
     * @param ui User interface for displaying status of command
     * @param tasks List of tasks for commands that update it
     * @param storage Storing of tasks for commands that update it
     * @throws DukeException If execution encounters errors
     */
    public abstract void execute(UI ui, TaskList tasks, Storage storage) throws DukeException;

    public String getCommandStatus() {
        return this.commandStatus;
    };

}
