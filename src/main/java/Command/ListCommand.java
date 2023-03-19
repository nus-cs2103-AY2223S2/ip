package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command by user to Duke to list all the task user has
 */
public class ListCommand extends Command {

    /**
     * Checks if command is an exit command.
     *
     * @return false List task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Prints the list of task User has.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.listTaskResponse(tasks);
    }

}
