package commands;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ITask;
import tasks.Todo;
import utilities.Parser;

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
                + getParser().getTaskManager().size() + " tasks in the list.");

        return false;

    }
}
