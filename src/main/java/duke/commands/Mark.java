package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.ITask;
import duke.utilities.Parser;

public class Mark extends ICommand {

    public Mark(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTaskManager().mark(getParser().getIndex(), true);
        setMsg("Nice! I've marked this task as done:\n" + t);
        return false;
    }
}
