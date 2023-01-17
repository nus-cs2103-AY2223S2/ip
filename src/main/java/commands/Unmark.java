package commands;

import exceptions.DukeException;
import tasks.ITask;
import uitilties.Parser;
import uitilties.UserInterface;

public class Unmark extends ICommand{
    public Unmark(Parser parser) {
        super(parser);
    }
    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTasks().get(getParser().getIndex());
        t.markAsUnDone();

        UserInterface.Speak("OK, I've marked this task as not done yet:\n" + t);
        return false;
    }
}
