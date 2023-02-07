package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.gui.Ui;

/**
 * Abstract class for duke.commands.
 */
public abstract class Command {
    /**
     * Executes the command, calling whatever methods necessary from Ui instance and modifying
     *      the TaskList if required.
     * @param ui The Duke Ui reference.
     * @param list The Duke TaskList reference.
     * @param command The full input command string for verification and further parsing.
     * @return The output of executing the command.
     * @throws DukeException If there is an error in running the command.
     */
    public abstract String execute (Ui ui, TaskList list, String command) throws DukeException;
}
