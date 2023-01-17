package commands;

import exceptions.DukeException;
import tasks.ITask;
import uitilties.Parser;
import uitilties.UserInterface;

public class Mark extends ICommand {

    public Mark(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        ITask t = getParser().getTasks().get(getParser().getIndex());
        t.markAsDone();
        UserInterface.Speak("Nice! I've marked this task as done:\n" + t);

        return false;
    }
}
