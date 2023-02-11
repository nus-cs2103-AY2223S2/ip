package vic.commands;

import vic.utilities.Parser;

/**
 * Represents exit action command. A <code>Exit</code> object corresponds to
 * the action exit from program
 */
public class Exit extends ICommand {
    public Exit(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() {
        setMsg("Bye. Hope to see you again soon!");
        return true;
    }
}
