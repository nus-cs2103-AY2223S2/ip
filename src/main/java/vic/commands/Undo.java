package vic.commands;

import vic.exceptions.DukeException;
import vic.utilities.Parser;
/**
 * Represents Undo tasks action command. A <code>Undo</code> object corresponds to
 * the action to Undo an action in the program
 */
public class Undo extends ICommand {

    public Undo(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        String result = getParser().getTaskManager().undo();
        setMsg(result);
        return false;

    }
}
