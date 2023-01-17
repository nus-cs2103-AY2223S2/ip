package commands;

import exceptions.DukeException;
import uitilties.Parser;

public abstract class ICommand {
    public Parser getParser() {
        return _parser;
    }

    private final Parser _parser;
    public ICommand(Parser parser) {
        this._parser = parser;
    }
    public abstract boolean run() throws DukeException;
}
