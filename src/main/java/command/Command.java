package command;

import util.DukeException;
import util.Parser;

public abstract class Command {
    public int extractIndex(String string) {
        if (!Parser.isNumeric(string)) {
            return 0;
        }
        return Integer.parseInt(string);
    }

    public abstract void executeCommand() throws DukeException;

}
