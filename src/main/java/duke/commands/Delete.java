package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.ITask;
import duke.utilities.Parser;

public class Delete extends ICommand {

    public Delete(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        ITask t = getParser().getTaskManager().remove(getParser().getIndex());


        setMsg("Noted. I've removed this task:\n" + t + "\nNow you have "
                + getParser().getTaskManager().size() + " in the list.");
        return false;
    }
}
