package command;

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
     * Abstract method to execute the command.
     *
     * @throws DukeException
     */
    public abstract void executeCommand() throws DukeException;

}
