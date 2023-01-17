package commands;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ITask;
import tasks.Todo;
import uitilties.Parser;
import uitilties.UserInterface;

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
                getParser().getTasks().add(task);
                break;
            case Deadlines:
                getParser().forDeadline();
                task = new Deadline(getParser().getDescription(), getParser().getBy());
                getParser().getTasks().add(task);
                break;
            default: //ToDos:
                getParser().forTodo();
                task = new Todo(getParser().getDescription());
                getParser().getTasks().add(task);
                break;

        }
        UserInterface.Speak(task + "\nAdded"+ "\nNow you have "
                + getParser().getTasks().size() + " tasks in the list.");
        return false;

    }
}
