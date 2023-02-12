package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.Deadline;
import vic.tasks.ITask;
import vic.utilities.Parser;

/**
 * Represents AddDeadline action command. A <code>AddDeadline</code> object corresponds to
 * the action adding a Deadline task to task list
 */
public class AddDeadline extends ICommand {
    public AddDeadline(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        getParser().forDeadline();
        ITask task = new Deadline(getParser().getDescription(), getParser().getBy());
        getParser().getTaskManager().add(task);
        setMsg(task + "\nAdded" + "\nNow you have " + getParser()
                .getTaskManager().size() + " tasks in the list.");

        return false;

    }
}
