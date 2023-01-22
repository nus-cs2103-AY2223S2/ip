package commands;

import exceptions.DukeException;
import tasks.ITask;
import utilities.Parser;

public class Unmark extends ICommand{
    public Unmark(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTaskManager().mark(getParser().getIndex(), false);
        setMsg("OK, I've marked this task as not done yet:\n" + t);
        return false;
    }
}
