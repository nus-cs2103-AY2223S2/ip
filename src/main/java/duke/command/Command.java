package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A base class representing a command in Duke. */
public abstract class Command {

    /**
     * Executes the command.
     * 
     * @param taskList The TaskList used in the current session
     * @param ui       The Ui object used in the current session
     * @throws DukeException Thrown when the command cannot be executed
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Indicates whether this command will terminate the current Duke instance.
     * 
     * @return Whether the current command will terminate Duke
     */
    public boolean isExit() {
        return false;
    }
}
