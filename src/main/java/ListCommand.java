package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command by user to Duke to list all the task user has
 *
 * @ Karen
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
     * @param ui An Ui which allows for interaction between Duke and user.
     * @param storage A Storage enabling Duke to store memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTaskResponse(tasks);
    }

}
