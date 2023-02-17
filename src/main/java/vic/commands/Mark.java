package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.ITask;
import vic.utilities.Parser;

/**
 * Represents mark action command. A <code>Mark</code> object corresponds to
 * the action to mark a task in the program
 */
public class Mark extends ICommand {

    public Mark(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTaskManager().mark(getParser().getIndex());
        setMsg("Nice! I've marked this task as done:\n" + t);
        return false;
    }
}
