package command;

import task.TaskManager;
import util.DukeException;
import util.Parser;

/**
 * Abstract class that executes the command given by the user.
 */
public abstract class Command {

    /**
     * Extracts index of task in list which is given
     * by user in String format and parses it to int.
     *
     * @param string
     * @return integer index of task
     */
    public int extractIndex(String string) {
        if (!Parser.isNumeric(string)) {
            return 0;
        }
        return Integer.parseInt(string);
    }

    /**
     * Abstract method to execute a given command.
     * <p>
     * @param taskManager
     * @return Successful execution of command message
     * @throws DukeException
     */
    public abstract String executeCommand(TaskManager taskManager) throws DukeException;
}
