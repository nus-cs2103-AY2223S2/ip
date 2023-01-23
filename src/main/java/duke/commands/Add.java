package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ITask;
import duke.tasks.Todo;
import duke.utilities.Parser;

/**
 * Represents add action command. A <code>Add</code> object corresponds to
 * the action adding a task to task list
 */
public class Add extends ICommand {
    public Add(Parser parser) {
        super(parser);
    }


    @Override
    public boolean run() throws DukeException {
        ITask task;
        switch (getParser().getType()) {
            case Events:
                getParser().forEvent();
                task = new Event(getParser().getDescription(), getParser().getFrom(),getParser().getTo());
                getParser().getTaskManager().add(task);
                break;
            case Deadlines:
                getParser().forDeadline();
                task = new Deadline(getParser().getDescription(), getParser().getBy());
                getParser().getTaskManager().add(task);
                break;
            default:
                getParser().forTodo();
                task = new Todo(getParser().getDescription());
                getParser().getTaskManager().add(task);
                break;

        }
        setMsg(task + "\nAdded"+ "\nNow you have "
                + getParser().getTaskManager().size() + " duke.tasks in the list.");

        return false;

    }
}
