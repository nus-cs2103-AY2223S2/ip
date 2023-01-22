package commands;

import utilities.Parser;


public class Exit extends ICommand{
    public Exit(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() {
        setMsg("Bye. Hope to see you again soon!");
        return true;
    }
}
