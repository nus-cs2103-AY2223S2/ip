package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.ITask;
import vic.utilities.Parser;

/**
 * Represents unmark action command. A <code>Unmark</code> object corresponds to
 * the action to unmark a task in the program
 */
public class Unmark extends ICommand {
    public Unmark(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTaskManager().unmark(getParser().getIndex());
        setMsg("OK, I've marked this task as not done yet:\n" + t);
        return false;
    }
}
