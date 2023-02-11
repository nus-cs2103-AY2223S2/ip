package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.ITask;
import vic.utilities.Parser;
/**
 * Represents delete action command.
 * A <code>Delete</code> object corresponds to
 * the action delete a task from task list
 */
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
