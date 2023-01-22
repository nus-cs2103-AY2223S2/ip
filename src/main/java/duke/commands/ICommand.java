package duke.commands;

import duke.exceptions.DukeException;
import duke.utilities.Parser;

public abstract class ICommand {
    public Parser getParser() {
        return _parser;
    }
    private String _msg = "Starting Command";

    private final Parser _parser;
    public ICommand(Parser parser) {
        this._parser = parser;
    }
    public abstract boolean run() throws DukeException;

    public String getMsg() {
        return _msg;
    }

    public void setMsg(String _msg) {
        this._msg = _msg;
    }
}
