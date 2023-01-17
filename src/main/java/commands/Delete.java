package commands;

import exceptions.DukeException;
import tasks.ITask;
import uitilties.Parser;
import uitilties.UserInterface;

public class Delete extends ICommand {

    public Delete(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        ITask t = getParser().getTasks().remove(getParser().getIndex());

        UserInterface.Speak("Noted. I've removed this task:\n" + t + "\nNow you have "
                + getParser().getTasks().size() + " in the list.");

        return false;
    }
}
