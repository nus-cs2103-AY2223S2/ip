package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;

/**
 * Command: to list out the commands
 */
public class ListCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(Views.EMPTY_LIST_STRING);
        }
        Ui.showList(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        return Ui.stringList(tasks, true, false);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
