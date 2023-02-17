package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.Event;
import vic.tasks.ITask;
import vic.utilities.Parser;

/**
 * Represents Event action command. A <code>Event</code> object corresponds to
 * the action adding an Event task to task list
 */
public class AddEvent extends ICommand {
    public AddEvent(Parser parser) {
        super(parser);
    }
    @Override
    public boolean run() throws DukeException {
        getParser().forEvent();
        ITask task = new Event(getParser().getDescription(),
                getParser().getFrom(), getParser().getTo());
        getParser().getTaskManager().add(task);
        setMsg(task + "\nAdded" + "\nNow you have " + getParser()
                .getTaskManager().size() + " tasks in the list.");

        return false;

    }
}
