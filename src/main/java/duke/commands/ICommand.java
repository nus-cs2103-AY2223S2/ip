package duke.commands;

import duke.exceptions.DukeException;
import duke.utilities.Parser;
/**
 * Represents action command. A <code>ICommand</code> abstract class corresponds to
 * the action to execute
 */
public abstract class ICommand {
    public Parser getParser() {
        return _parser;
    }

    private String _msg = "Starting Command";

    private final Parser _parser;

    /**
     * To create a ICommand object which manipulate the task
     *
     * @param parser in-charge to convert user input to valid program input command
     */
    public ICommand(Parser parser) {
        this._parser = parser;
    }

    /**
     * Set the task message once the task is done
     *
     * @throws DukeException IF error occur during execution of task.
     */
    public abstract boolean run() throws DukeException;

    public String getMsg() {
        return _msg;
    }

    public void setMsg(String _msg) {
        this._msg = _msg;
    }
}
