package commands;

import uitilties.Parser;
import uitilties.UserInterface;


public class Exit extends ICommand{
    public Exit(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() {
        UserInterface.Speak("Bye. Hope to see you again soon!");
        return true;
    }
}
